package com.payandpark.payandpark.parkingslot.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingSlotRowMapper implements RowMapper<ParkingSlot> {
    @Override
    public ParkingSlot mapRow(ResultSet resultSet, int i) throws SQLException {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setId(resultSet.getInt("id"));
        parkingSlot.setType(resultSet.getString("type"));
        return parkingSlot;
    }
}
