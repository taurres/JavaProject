package com.wenjiaxi.oa.admin.leave.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.wenjiaxi.oa.admin.identity.entity.User;

/**
 * LeaveAudit实体
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:22:11
 * @version 1.0
 */

@Entity @Table(name="OA_LEAVE_AUDIT")
public class LeaveAudit implements Serializable{

	private static final long serialVersionUID = -8364156981191979879L;
	@Id @Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/** 与假期明细存在多对一关联 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=LeaveItem.class)
	@JoinColumn(name="ITEM_ID", referencedColumnName="ID",
			foreignKey=@ForeignKey(name="FK_AUDIT_ITEM_ID"))
	private LeaveItem leaveItem;
	/** 审核状态： 1 : 同意  2: 不同意 */
	@Column(name="STATUS", columnDefinition="smallint(6) default 1", nullable=false)
	private Short status;
	/** 与审核人存在多对一关联 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name="CHECKER", referencedColumnName="USER_ID",
			foreignKey=@ForeignKey(name="FK_AUDIT_CHECKER"))
	private User checker;
	@Column(name="CHECK_DATE")
	private Date checkDate;
	@Column(name="REMARK", length=500)
	private String remark;
	
	/** setter and getter method */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LeaveItem getLeaveItem() {
		return leaveItem;
	}
	public void setLeaveItem(LeaveItem leaveItem) {
		this.leaveItem = leaveItem;
	}
	public User getChecker() {
		return checker;
	}
	public void setChecker(User checker) {
		this.checker = checker;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
}