package com.example.weatherforecast.database;
import org.litepal.crud.DataSupport;
public class Province extends DataSupport {
    private int id;					// id 是每个实体类的主键，从1开始，0表示不存在
    private String provinceName;	//省名
    private int provinceCode;		//省代号，是JSON中的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
