package myDiplom.excel.read.FAO;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * It's util class for reading from excel
 */
public class ReadSheduler {
    private static File file = null;

    /**
     * ReadSheduler is FAO(file access object), not for creating. Use static methods
     */
    private ReadSheduler() {
    }

    public static void main(String[] args) throws IOException {
        File fileExcel = new File("Shududer_1kurs.xls");

        String[] strings = readFromExcelFacultets(fileExcel);
        for (String string : strings) {
            System.out.println(string);
        }

//        LinkedHashMap<String, List<Map<String, Integer>>> facGroupsFromExcel = getFacGroupsFromExcel(fileExcel);
//        for (Map.Entry<String, List<Map<String, Integer>>> entry : facGroupsFromExcel.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }
    }

    /**
     * read from excel, return map of facultets+groups in each facultet with column# of group
     *
     * @param fileExcel
     * @return
     * @throws IOException
     */
    public static LinkedHashMap<String, List<Map<String, Integer>>> getFacGroupsFromExcel(File fileExcel) throws IOException {
        file = fileExcel;
        LinkedHashMap<String, List<Map<String, Integer>>> map = new LinkedHashMap<>();
        Map<String, Integer> mapFacultetNameGroupStartNum = new LinkedHashMap<>();
        Map<Integer, Integer> mapStartFinishGroup = new LinkedHashMap<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileExcel);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumbForGroups = 0;

            for (Row row : sheet) {
                Cell cell0 = row.getCell(0);
                if (cell0 == null) {
                    continue;
                }

                if (cell0.getStringCellValue().equalsIgnoreCase("дни")) {

                    CellAddress address = cell0.getAddress();
                    HSSFRow row1 = sheet.getRow(address.getRow());

                    Iterator<Cell> iterator = row1.cellIterator();
                    iterator.next();
                    iterator.next();
                    iterator.next();
                    rowNumbForGroups = row1.getRowNum() + 1;

                    while (iterator.hasNext()) {

                        Cell cell = iterator.next();
                        if (!cell.getStringCellValue().isEmpty()) {

                            /**
                             * and now put facultets and groups start index in map
                             */
                            mapFacultetNameGroupStartNum.put(cell.getStringCellValue(), cell.getColumnIndex());
                        }
                    }

                    break;
                }
            }

            /**
             * now iter throw map to get groups from table
             */

            Row rowWithGroups = sheet.getRow(rowNumbForGroups);
            Collection<Integer> values = mapFacultetNameGroupStartNum.values();
            List<Integer> integers = new ArrayList<>();
            for (Integer value : values) {
                integers.add(value);
            }

            for (int i = 0; i < integers.size(); i++) {
                int next;
                try {
                    next = integers.get(i + 1);
                } catch (Exception e) {
                    next = integers.get(i);
                }
                mapStartFinishGroup.put(integers.get(i), next);
            }


            for (Map.Entry<String, Integer> entry : mapFacultetNameGroupStartNum.entrySet()) {
                int start = entry.getValue();
                int end = mapStartFinishGroup.containsKey(start) ? mapStartFinishGroup.get(start) : start;
                List<Map<String, Integer>> listGroupsForFac = new ArrayList<>();

                if (start != end) {
                    for (int i = start; i < end; i++) {
                        listGroupsForFac.add(Collections.singletonMap(rowWithGroups.getCell(i).getStringCellValue().trim(), i));
                    }
                } else {
                    listGroupsForFac.add(Collections.singletonMap(rowWithGroups.getCell(end).getStringCellValue(), end));
                }

                map.put(entry.getKey(), listGroupsForFac);
            }
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return map;
    }


    /**
     * read from excel file facultets, return array of fac
     *
     * @param fileExcel
     * @return array
     * @throws IOException
     */
    public static String[] readFromExcelFacultets(File fileExcel) throws IOException {
        List<String> list = new ArrayList<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileExcel);
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);


            for (Row row : sheet) {
                Cell cell0 = row.getCell(0);
                if (cell0 == null) {
                    continue;
                }

                if (cell0.getStringCellValue().equalsIgnoreCase("дни")) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    cellIterator.next();
                    cellIterator.next();
                    cellIterator.next();

                    int i = 2;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        if (!cell.getStringCellValue().isEmpty()) {
                            list.add(cell.getStringCellValue().trim());
                        }
                    }

                }
            }

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static List<Map<String, Integer>> getGroupsByFacultet(String facultet) {
        List<Map<String, Integer>> result = null;

        try {
            LinkedHashMap<String, List<Map<String, Integer>>> facGroupsFromExcel = getFacGroupsFromExcel(file);
            result = facGroupsFromExcel.get(facultet);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
