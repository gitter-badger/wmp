package com.qreal.wmp.db.robot.model.robot;

import com.qreal.wmp.thrift.gen.TRobot;
import lombok.Data;

import javax.persistence.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static javax.persistence.GenerationType.IDENTITY;

/** TRIK robot in dashboard service.*/
@Entity
@Table(name = "robots")
@Data
public class RobotSerial {

    /** Surrogate key for RobotSerial.*/
    private Long id;

    /** Name of robot (unique only in robot's group of owner).*/
    private String name;

    /** SSID of robot's WiFi.*/
    private String ssid;

    /** Owner of robot.*/
    private String owner;

    public RobotSerial() {
    }

    /** Constructor-converter from Thrift TRobot to RobotSerial.*/
    public RobotSerial(TRobot tRobot) {
        if (tRobot.isSetId()) {
            id = tRobot.getId();
        }

        if (tRobot.isSetName()) {
            name = tRobot.getName();
        }

        if (tRobot.isSetSsid()) {
            ssid = tRobot.getSsid();
        }

        if (tRobot.isSetUsername()) {
            owner = tRobot.getUsername();
        }
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id",
            unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ssid",
            nullable = false, length = 45)
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    /** Converter from RobotSerial to Thrift TRobot.*/
    public TRobot toTRobot() {
        TRobot tRobot = new TRobot();

        if (id != null) {
            tRobot.setId(id);
        }

        BiConsumer<String, Consumer<String>> setField = (value, func) -> {
            if (value != null) {
                func.accept(value);
            }
        };

        setField.accept(name, tRobot::setName);
        setField.accept(ssid, tRobot::setSsid);
        setField.accept(owner, tRobot::setUsername);

        return tRobot;
    }

}
