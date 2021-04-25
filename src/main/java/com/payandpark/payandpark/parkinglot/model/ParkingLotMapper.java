package com.payandpark.payandpark.parkinglot.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingLotMapper implements RowMapper<ParkingLot> {
    @Override
    public ParkingLot mapRow(ResultSet resultSet, int i) throws SQLException {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(resultSet.getInt("id"));
        return parkingLot;
    }
}
