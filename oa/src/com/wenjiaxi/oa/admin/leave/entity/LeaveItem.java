package com.wenjiaxi.oa.admin.leave.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.wenjiaxi.oa.admin.identity.entity.User;

/**
 * LeaveItem实体
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:20:36
 * @version 1.0
 */

@Entity @Table(name="OA_LEAVE_ITEM")
public class LeaveItem implements Serializable {
	
	private static final long serialVersionUID = -5579660102896044587L;
	@Id @Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/** 假期明细与假期类型存在多对一关联 */
	@ManyToOne(targetEntity=LeaveType.class, fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE_CODE", referencedColumnName="CODE", 
			foreignKey=@ForeignKey(name="FK_LEAVE_ITEM_TYPE"))
	private LeaveType leaveType;
	/** 开始日期 */
	@Column(name="BEGIN_DATE")
	private Date beginDate;
	/** 结束日期 */
	@Column(name="END_DATE")
	private Date endDate;
	/** 请假小时 */
	@Column(name="LEAVE_HOUR")
	private Float leaveHour;
	/** 请假原因 */
	@Column(name="LEAVE_CASE", length=300)
	private String leaveCase;
	/** 状态 */
	@Column(name="STATUS")
	private Short status = 0;
	/** 创建日期 */
	@Column(name="CREATE_DATE")
	private Date createDate;
	/** 创建人 */
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JoinColumn(name="CREATER", referencedColumnName="USER_ID",
			foreignKey=@ForeignKey(name="FK_LEAVE_ITEM_CREATER"))
	private User creater;
	/** PROC_instance_ID	Varchar2(100)	流程实例ID	*/
	@Column(name="PROC_INSTANCE_ID", length=100)
	private String procInstanceId;
	
	/** 定义任务ID */
	@Transient // 不是持久化属性，不会生成表中的列名
	private String taskId;
	
	/** setter and getter method */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Float getLeaveHour() {
		return leaveHour;
	}
	public void setLeaveHour(Float leaveHour) {
		this.leaveHour = leaveHour;
	}
	public String getLeaveCase() {
		return leaveCase;
	}
	public void setLeaveCase(String leaveCase) {
		this.leaveCase = leaveCase;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getCreater() {
		return creater;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	public String getProcInstanceId() {
		return procInstanceId;
	}
	public void setProcInstanceId(String procInstanceId) {
		this.procInstanceId = procInstanceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}