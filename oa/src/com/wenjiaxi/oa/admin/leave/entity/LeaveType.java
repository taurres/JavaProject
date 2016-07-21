package com.wenjiaxi.oa.admin.leave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.wenjiaxi.oa.admin.identity.entity.User;

/**
 * LeaveType实体
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:19:38
 * @version 1.0
 */

@Entity @Table(name="OA_LEAVE_TYPE")
public class LeaveType implements Serializable {

	private static final long serialVersionUID = -7990925752412350100L;
	@Id @Column(name="CODE", length=255)
	private String code;
	/** 名称 */
	@Column(name="NAME", length=100)
	private String name;
	/** 备注 */
	@Column(name="REMARK", length=500)
	private String remark;
	/** 修改人 */
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JoinColumn(name="MODIFIER", referencedColumnName="USER_ID", 
			foreignKey=@ForeignKey(name="FK_LEAVETYPE_MODIFIER"))
	private User modifier;
	/** 修改日期 */
	@Column(name="MODIFY_DATE")
	private Date modifyDate;
	/** 创建人 */
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
	@JoinColumn(name="CREATER", referencedColumnName="USER_ID",
			foreignKey=@ForeignKey(name="FK_LEAVETYPE_CREATER"))
	private User creater;
	/** 创建日期 */
	@Column(name="CREATE_DATE")
	private Date createDate;
	// 假期类型与假期明细存在一对多关联
	@OneToMany(targetEntity=LeaveItem.class,cascade=CascadeType.ALL,
			fetch=FetchType.LAZY, mappedBy="leaveType", orphanRemoval=true)
	private Set<LeaveItem> leaveItems = new HashSet<LeaveItem>(0);
	
	/** setter and getter method */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public User getCreater() {
		return creater;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Set<LeaveItem> getLeaveItems() {
		return leaveItems;
	}
	public void setLeaveItems(Set<LeaveItem> leaveItems) {
		this.leaveItems = leaveItems;
	}
}
