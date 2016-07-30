package com.wenjiaxi.oa.admin.addressbook.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Contact实体
 * @author WEN JIAXI
 * @date 2016年7月15日 下午8:02:23
 * @version 1.0
 */

@Entity @Table(name="OA_AB_CONSTACT")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 5150091025769768827L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	@Column(name="NAME", length=50)
	private String name;
	@Column(name="SEX")
	private Short sex = 1;
	@Column(name="BIRTHDAY")
	private Date birthday;
	@Column(name="PHONE", length=50)
	private String phone;
	@Column(name="EMAIL", length=50)
	private String email;
	@Column(name="QQ_NUM", length=50)
	private String qqNum;
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ContactGroup.class)
	@JoinColumn(name="GROUP_ID", referencedColumnName="ID",
			foreignKey=@ForeignKey(name="FK_CONTACT_GROUP"))
	private ContactGroup contactGroup;
	
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
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public ContactGroup getContactGroup() {
		return contactGroup;
	}
	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}
}
