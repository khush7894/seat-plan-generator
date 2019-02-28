package com.seating.plan.generator.system.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-02-08T13:32:24.051+0530")
@StaticMetamodel(RoomInfo.class)
public class RoomInfo_ {
	public static volatile SingularAttribute<RoomInfo, Integer> id;
	public static volatile SingularAttribute<RoomInfo, Date> createdDate;
	public static volatile SingularAttribute<RoomInfo, String> fullQuantity;
	public static volatile SingularAttribute<RoomInfo, String> halfQuantity;
	public static volatile SingularAttribute<RoomInfo, Integer> isDeleted;
	public static volatile SingularAttribute<RoomInfo, String> roomName;
	public static volatile SingularAttribute<RoomInfo, Date> updatedDate;
}
