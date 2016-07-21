package com.wenjiaxi.oa.admin.identity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 
 * @author WEN JIAXI
 * @date 2016年7月15日 下午7:39:18
 * @version 1.0
 */
@Entity @Table(name="OA_ID_ROLE")
public class Role implements Serializable{

	private static final long serialVersionUID = 5293026989527761155L;
	
	/* ID	NUMBER	ID	PK */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	/* NAME	VARCHAR2(50)	角色名字	 */
	@Column(name="NAME", length=50)
	private String name;
	
	/* REMARK	VARCHAR2(500)	备注		*/
	@Column(name="REMARK", length=500)
	private String remark;
	
	/* CREATER	VARCHAR2(50)	创建人	FK(OA_ID_USER) */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name="CREATER", referencedColumnName="USER_ID", foreignKey=@ForeignKey(name="FK_ROLE_CREATER"))
	private User creater;
	
	/* CREATE_DATE	DATE	创建时间	 */
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	/* MODIFIER	VARCHAR2(50)	修改人	FK(OA_ID_USER) */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name="MODIFIER", referencedColumnName="USER_ID", foreignKey=@ForeignKey(name="FK_ROLE_MODIFIER"))
	private User modifier;
	
	/* MODIFY_DATE	DATE	修改时间	 */
	@Column(name="MODIFY_DATE")
	private Date modifyDate;
	
	/* ROLE_ID	NUMBER	角色ID	FK(OA_ID_ROLE)		 */
	@ManyToMany(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinTable(name="OA_ID_USER_ROLE", joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"),
		inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
	private Set<User> users = new HashSet<>();
	
	
	/** setter and getter method */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
}
