package com.commin.pro.shinhancard.util;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;

import com.commin.pro.shinhancard.model.Model2Use;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by user on 2017-08-08.
 */
public class Util2Excel {

    public static ArrayList<Model2Use> getMapDataToExcel(Context context,int sheet_num) {
        Workbook workbook = null;
        Sheet sheet = null;
        Cell cell[] = null;
        ArrayList<Model2Use> arrayList = new ArrayList<Model2Use>();
        try {
            InputStream is = context.getResources().getAssets().open("shinhan_use.xls");
            workbook = Workbook.getWorkbook(is);
            if (workbook != null) {
                sheet = workbook.getSheet(sheet_num);
                if (sheet != null) {
                    int rows = sheet.getRows();
                    for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                        cell = sheet.getRow(rowIndex);
                        Model2Use model = new Model2Use();
                        model.setUse_date(cell[0].getContents());
                        model.setUse_confirm_date(cell[1].getContents());
                        model.setBrand(cell[2].getContents());
                        model.setShop_name(cell[3].getContents());
                        model.setPrice(cell[4].getContents());
                        model.setHow_use(cell[5].getContents());
                        model.setSuc_number(cell[6].getContents());
                        model.setCheck_date(cell[7].getContents());
                        arrayList.add(model);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }


        if (arrayList.isEmpty()) {
            return null;
        } else
            return arrayList;
    }



    public static ArrayList<Model2Use> getMapDataToExcelAccept(Context context,int sheet_num) {
        Workbook workbook = null;
        Sheet sheet = null;
        Cell cell[] = null;
        ArrayList<Model2Use> arrayList = new ArrayList<Model2Use>();
        try {
            InputStream is = context.getResources().getAssets().open("shinhan_accept.xls");
            workbook = Workbook.getWorkbook(is);
            if (workbook != null) {
                sheet = workbook.getSheet(sheet_num);
                if (sheet != null) {
                    int rows = sheet.getRows();
                    for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                        cell = sheet.getRow(rowIndex);
                        Model2Use model = new Model2Use();
                        model.setUse_date(cell[0].getContents());
                        model.setSuc_number(cell[1].getContents());
                        model.setShop_name(cell[5].getContents());
                        model.setPrice(cell[6].getContents());
                        model.setHow_use(cell[7].getContents());
                        model.setUse_confirm_date(cell[8].getContents());

                        model.setBrand(cell[4].getContents());
                        arrayList.add(model);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }


        if (arrayList.isEmpty()) {
            return null;
        } else
            return arrayList;
    }
}
