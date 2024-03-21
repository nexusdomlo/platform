package com.example.platform.mapper;

import com.example.platform.pojo.Rent;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RenterMapper {
    @Select("select * from renttable where student_num=#{student_num}")
    Rent findByStudentNum(String student_num);
    @Insert("insert into renttable(student_num,car_id,car_name,phone,renter,answer)"
            +"values(#{student_num},#{car_id},#{car_name},#{phone},#{renter},#{answer})")
    void renterAdd(Rent rent);
    @Update("update renttable set answer=#{answer} where car_id=#{car_id}")
    void updateRenter(Rent rent);
    @Select("select * from renttable where renter=#{renter}")
    Rent findRenter(String renter);
    @Delete("delete from renttable where car_id=#{car_id}")
    void removeRenter(String car_id);
}
