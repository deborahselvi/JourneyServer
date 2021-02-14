package com.journey.map.repository.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_data")
public class UserData {
   @Id
   @SequenceGenerator(name = "useridgenerator", sequenceName = "user_data_id_seq", initialValue = 1, allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "useridgenerator")
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "room_id", nullable = false)
   private Long roomId;

   @Column(name = "username")
   private String username;

   @Column(name = "created")
   private Date created;
}
