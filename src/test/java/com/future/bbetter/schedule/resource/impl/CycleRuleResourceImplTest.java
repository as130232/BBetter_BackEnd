package com.future.bbetter.schedule.resource.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.future.bbetter.exception.customize.DataNotFoundException;
import com.future.bbetter.exception.customize.InsertOrUpdateDataFailureException;
import com.future.bbetter.member.model.Member;
import com.future.bbetter.schedule.constant.CYCLE_RULE;
import com.future.bbetter.schedule.constant.SCHEDULE_OWNER;
import com.future.bbetter.schedule.dto.CycleRuleDto;
import com.future.bbetter.schedule.dto.ScheduleDto;
import com.future.bbetter.schedule.model.CycleRule;
import com.future.bbetter.schedule.model.Schedule;
import com.future.bbetter.schedule.model.ScheduleOwner;
import com.future.bbetter.schedule.model.ScheduleType;
import com.future.bbetter.schedule.resource.CycleRuleResource;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@Import(CycleRuleResourceImpl.class)
public class CycleRuleResourceImplTest {
	
	@Autowired
	private TestEntityManager entityMgr;
	
	@Autowired
	private CycleRuleResource cycleRs;
	
	Schedule schedule1;
	CycleRule cycleRule;
	int isValid = CYCLE_RULE.IS_VALID_YES.value;
	int period = CYCLE_RULE.PERIOD_MONTHLY.value;
	int timePoint = 10;
	
	@Before
	public void setup() throws Exception {
		Member john = new Member();
		john.setName("john");
		john.setEmail("john@gmail.com");
		john.setPassword("password");
		john.setGender(1);
		john.setAddress("North Korea");
		john.setMoney(new BigDecimal(100));
		entityMgr.persistAndFlush(john);
		
		ScheduleOwner owner = new ScheduleOwner();
		owner.setRegistrantId(john.getMemberId());
		owner.setSource(SCHEDULE_OWNER.SOURCE_MEMBER.value);
		owner.setIsValid(1);
		owner.setCreatedate(new Date());
		owner.setUpdatedate(new Date());
		entityMgr.persist(owner);
		
		ScheduleType type = new ScheduleType();
		type.setName("test_type");
		entityMgr.persistAndFlush(type);
		
		Instant now = Instant.now();
		Instant afterTwoHrs = LocalDateTime.now().plusHours(2)
				.toInstant(ZoneOffset.UTC);
		
		schedule1 = new Schedule();
		schedule1.setScheduleType(type);
		schedule1.setStartTime(Date.from(now));
		schedule1.setEndTime(Date.from(afterTwoHrs));
		schedule1.setName("Test_Schedule");
		schedule1.setStatus(1);
		schedule1.setVisibility(2);
		schedule1.setIsCycle(0);
		schedule1.setIsNeedRemind(0);
		schedule1.setIsTeamSchedule(0);
		schedule1.setIsValid(0);
		schedule1.setCreatedate(new Date());
		entityMgr.persistAndFlush(schedule1);
		
		

		cycleRule = new CycleRule();
		cycleRule.setSchedule(schedule1);
		cycleRule.setIsValid(isValid);
		cycleRule.setPeriod(period);
		cycleRule.setTimePoint(timePoint);
		cycleRule.setCreatedate(new Date());
		cycleRule.setUpdatedate(new Date());
		entityMgr.persistAndFlush(cycleRule);
	}

	
	/***
	 * test addCycleRule() method
	 * 給予正確資料應可成功塞入資料庫
	 */
	@Test
	public void whenAddCycleRule_thenSuccess() {
		//given
		CycleRuleDto data = new CycleRuleDto();
		data.setIsValid(isValid);
		data.setPeriod(period);
		data.setTimePoint(timePoint);
		data.setScheduleDto(ScheduleDto.from(schedule1));
		
		//when
		CycleRuleDto result = cycleRs.addCycleRule(data);
		
		//then
		assertThat(result).isNotNull();
		assertThat(result.getCycleRuleId()).isNotNull();
		assertThat(result.getPeriod()).isEqualTo(period);
		assertThat(result.getTimePoint()).isEqualTo(timePoint);
	}
	
	
	/***
	 * test addCycleRule() method
	 * 給予資料中必要的資料為null,則應丟出例外
	 */
	@Test
	public void whenAddCycleRuleAndNoScheduleData_thenThrowsException() {
		//given
		CycleRuleDto data = new CycleRuleDto();
		data.setIsValid(isValid);
		data.setPeriod(period);
		data.setTimePoint(timePoint);
		data.setScheduleDto(null);
		
		//when
		Throwable thrown = catchThrowable(()->{
			cycleRs.addCycleRule(data);
		});
		
		//then
		assertThat(thrown).isInstanceOf(InsertOrUpdateDataFailureException.class)
			.hasMessageContaining("It can't found Schedule data in CycleRuleDto: ");
	}
	
	
	/***
	 * test addCycleRule() method
	 * 給予資料中週期值為錯誤,則應丟出例外
	 */
	@Test
	public void whenAddCycleRuleAndIllegalPeriodValue_thenThrowsException() {
		//given
		int illegalPeriod = -3;
		//check this period value is illegal.
		assertThat(CYCLE_RULE.validatePeriod(illegalPeriod)).isEqualTo(false);
		CycleRuleDto data = new CycleRuleDto();
		data.setIsValid(isValid);
		data.setPeriod(illegalPeriod);
		data.setTimePoint(timePoint);
		data.setScheduleDto(ScheduleDto.from(schedule1));
		
		//when
		Throwable thrown = catchThrowable(()->{
			cycleRs.addCycleRule(data);
		});
		
		//then
		assertThat(thrown).isInstanceOf(InsertOrUpdateDataFailureException.class)
			.hasMessageContaining("The period value is illegal in CycleRuleDto: ");
	}

	
	/***
	 * test updateCycleRule() method
	 * 給予正確資料應可成功塞入資料庫
	 */
	@Test
	public void whenUpdateCycleRule_thenSuccess() {
		//given
		long oldId = cycleRule.getCycleRuleId();
		CycleRuleDto newData = CycleRuleDto.from(cycleRule);
		int newPeriod = CYCLE_RULE.PERIOD_WEEKLY.value;
		newData.setPeriod(newPeriod);

		
		//when
		cycleRs.updateCycleRule(newData);
		
		//then
		CycleRule found = entityMgr.find(CycleRule.class, oldId);
		assertThat(found).isNotNull();
		assertThat(found.getCycleRuleId()).isEqualTo(oldId);
		assertThat(found.getSchedule().getScheduleId())
			.isEqualTo(schedule1.getScheduleId());
		assertThat(found.getPeriod())
			.isEqualTo(newPeriod);
	}
	
	
	/***
	 * test updateCycleRule() method
	 * 給予資料中必要的資料為null,則應丟出例外
	 */
	@Test
	public void whenUpdateCycleRuleAndNoScheduleData_thenThrowsException() {
		//given
		CycleRuleDto newData = CycleRuleDto.from(cycleRule);
		newData.setScheduleDto(null);

		
		//when
		Throwable thrown = catchThrowable(()->{
			cycleRs.updateCycleRule(newData);
		});
		
		//then
		assertThat(thrown).isInstanceOf(InsertOrUpdateDataFailureException.class)
			.hasMessageContaining("It can't found Schedule data in CycleRuleDto: ");
	}

	
	/***
	 * test updateCycleRule() method
	 * 給予資料中週期值為錯誤,則應丟出例外
	 */
	@Test
	public void whenUpdateCycleRuleAndIllegalPeriodValue_thenThrowsException() {
		//given
		int illegalPeriod = -3;
		//check this period value is illegal.
		assertThat(CYCLE_RULE.validatePeriod(illegalPeriod)).isEqualTo(false);
		CycleRuleDto newData = CycleRuleDto.from(cycleRule);
		newData.setPeriod(illegalPeriod);
		
		//when
		Throwable thrown = catchThrowable(()->{
			cycleRs.updateCycleRule(newData);
		});
		
		//then
		assertThat(thrown).isInstanceOf(InsertOrUpdateDataFailureException.class)
			.hasMessageContaining("The period value is illegal in CycleRuleDto: ");
	}
	
	/***
	 * test deleteCycleRule() method
	 * 給予正確資料應可成功塞入資料庫
	 */
	@Test
	public void whenDeleteCycleRule_thenSuccess() {
		//given
		long cycleRuleId = cycleRule.getCycleRuleId();
		
		//when
		cycleRs.deleteCycleRule(cycleRuleId);
		
		//then
		CycleRule result = entityMgr.find(CycleRule.class, cycleRuleId);
		assertThat(result).isNull();
	}

	
	/***
	 * test getCycleRule() method
	 * 給予正確id應回傳一筆資料
	 */
	@Test
	public void whenGetCycleRule_thenReturnOneRecord() {
		//given
		long cycleRuleId = cycleRule.getCycleRuleId();
		
		//when
		CycleRuleDto result = cycleRs.getCycleRule(cycleRuleId);
		
		//then
		assertThat(result).isNotNull();
		assertThat(result.getCycleRuleId()).isEqualTo(cycleRuleId);
	
	}
	
	
	/***
	 * test getCycleRule() method
	 * 給予資料中必要的資料為null,則應丟出例外
	 */
	@Test
	public void whenGetCycleRuleAndNotFound_thenThrowsException() {
		//given
		long cycleRuleId = -99L;
		assertThat(entityMgr.find(CycleRule.class, cycleRuleId)).isNull();
		
		//when
		Throwable thrown = catchThrowable(()->{
			cycleRs.getCycleRule(-99L);
		});
		
		//then
		assertThat(thrown).isInstanceOf(DataNotFoundException.class)
			.hasMessageContaining("There is no CycleRule data in database, id:");
	
	}

	/***
	 * test getCycleRulesByScheduleId() method
	 * 給予正確id資料應回傳含有一筆資料的list
	 */
	@Test
	public void whenGetCycleRulesByScheduleId_thenReturnOneRecord() {
		//given
		Long scheduleId = schedule1.getScheduleId();
		
		
		//when
		List<CycleRuleDto> results = cycleRs.getCycleRulesByScheduleId(scheduleId);
		
		//then
		assertThat(results).isNotNull();
		assertThat(results.size()).isEqualTo(1);
		
		CycleRuleDto result = results.get(0);
		assertThat(result.getScheduleDto().getScheduleId())
			.isEqualTo(scheduleId);
		assertThat(result.getTimePoint()).isEqualTo(timePoint);
	}
	
	
	/***
	 * test getCycleRulesByScheduleId() method
	 * 給予找不到資料的id的資料應回傳無資料的list
	 */
	@Test
	public void whenGetCycleRulesByScheduleIdAndNotFound_thenReturnEmptyList() {
		//given
		long scheduleId = -99;
		assertThat(entityMgr.find(Schedule.class, scheduleId)).isNull();
		
		//when
		List<CycleRuleDto> results = cycleRs.getCycleRulesByScheduleId(scheduleId);
		
		//then
		assertThat(results).isNotNull();
		assertThat(results.size()).isEqualTo(0);
	}

	
	/***
	 * test getCycleRuleForOptional() method
	 * 給予找不到資料的id的資料則使用時會丟出例外
	 */
	@Test
	public void whenGetCycleRuleForOptionalAndNotFound_thenThrowsException() {
		//given 
		CycleRule cycle = new CycleRule();
		cycle.setSchedule(schedule1);
		cycle.setPeriod(10);
		cycle.setIsValid(1);
		cycle.setTimePoint(0307);
		cycle.setCreatedate(new Date());
		cycle.setUpdatedate(new Date());
		entityMgr.persist(cycle);
		
		//when
		Optional<CycleRuleDto> result = cycleRs.getCycleRuleForOptional(-99L);
		Throwable thrown = catchThrowable(()->{
			result.get();
		});
		
		//then
		assertThat(result).isNotNull();
		assertThat(thrown).isInstanceOf(NoSuchElementException.class);
	}

}
