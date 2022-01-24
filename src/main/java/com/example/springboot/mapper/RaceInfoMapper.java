package com.example.springboot.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.springboot.dto.RaceInfoDTO;


@Mapper
public interface RaceInfoMapper {
	ArrayList<RaceInfoDTO> getAllRaceInfo();
	
	RaceInfoDTO getRaceInfoById(Integer raceId);
	
	

}
