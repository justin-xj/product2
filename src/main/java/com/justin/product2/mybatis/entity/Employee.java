package com.justin.product2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;

import com.justin.product2.mybatis.Enums.Gender;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author justin
 * @since 2020-05-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = 1L;
    @TableId
    private Integer empNo;

    private LocalDate birthDate;

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate hireDate;


    @Override
    protected Serializable pkVal() {
        return this.empNo;
    }

}
