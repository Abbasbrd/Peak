package com.anar.PEAK;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;


import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//import org.apache.commons.
interface IPermissionCallback{
    void askForStoragePermission();
    Boolean checkSelfStoragePermission();
}
public class Excel_Util_POI {

    private final Context context;
    private final List<Post> postList;
    private WritableWorkbook workbook;


    /**
     * A class that write and read  excel 2007 file (xlsx)
     * using the jxl library.
     */
    public Excel_Util_POI(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }
    void createExcelSheet() {


        String xlsFile = "PostList.xls";
        java.io.File futureStudioIconFile = new java.io.File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                + "/" + xlsFile);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        try {
//            Toast.makeText(context,"0" , Toast.LENGTH_SHORT).show();

            showStorageAlertDialog();

            workbook = Workbook.createWorkbook(futureStudioIconFile, wbSettings);
//            Toast.makeText(context,"1" , Toast.LENGTH_SHORT).show();

            createFirstSheet(workbook);
//            Toast.makeText(context,"2" +"" , Toast.LENGTH_SHORT).show();


            workbook.write();
                    Toast.makeText(context,"write wb executed" , Toast.LENGTH_SHORT).show();

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createFirstSheet(WritableWorkbook workbook) {
        try {

            //Excel sheet name. 0 (number)represents first sheet
            String sheetName = getCurrentUsername();
            WritableSheet sheet = workbook.createSheet(sheetName, 0);
//            Toast.makeText(context,"createSheet  executed" , Toast.LENGTH_SHORT).show();

            // set the title of columns
            int fc = 5;
            setSheetHeader(sheet, fc);
//            Toast.makeText(context,"setSheetHeader  executed" , Toast.LENGTH_SHORT).show();

            // Save the records of load
            setSheetrow(sheet, postList);
//            Toast.makeText(context,"setSheetrow  executed" , Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write_post() {

        try {



            createExcelSheet();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void setSheetHeader(WritableSheet sheet, int fc) {
        try {

            // Write Headers for the Post total Load
            int r = 0;
            int c = 0;
            sheet.addCell(new Label(c, r, "کدپست"));c+=1;
            sheet.addCell(new Label(c, r, "ظرفیت"));c+=1;
            sheet.addCell(new Label(c, r, "تاریخ"));c+=1;
            sheet.addCell(new Label(c, r, "ساعت"));c+=1;
            sheet.addCell(new Label(c, r, "R"));c+=1;
            sheet.addCell(new Label(c, r, "S"));c+=1;
            sheet.addCell(new Label(c, r, "T"));c+=1;
            sheet.addCell(new Label(c, r, "N"));c+=1;
            sheet.addCell(new Label(c, r, "V_TR"));c+=1;
            sheet.addCell(new Label(c, r, "V_ST"));c+=1;
            sheet.addCell(new Label(c, r, "V_RS"));c+=1;
            sheet.addCell(new Label(c, r, "V_RN"));c+=1;
            sheet.addCell(new Label(c, r, "V_SN"));c+=1;
            sheet.addCell(new Label(c, r, "V_TN"));c+=1;



            // Write Headers for the Feeders Load

            for (int index = 1; index <= fc; index++) {
                sheet.addCell(new Label(c, r, "F"+ index+"_R"));
                sheet.addCell(new Label(c+1, r, "F"+ index+"_S"));
                sheet.addCell(new Label(c+2, r, "F"+ index+"_T"));
                sheet.addCell(new Label(c+3, r, "F"+ index+"_N"));
                c += 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSheetrow(WritableSheet sheet, List<Post> PostList) {
        int maxFeederCount = 1;

        try {
            int r = 1;
            for (Post post : PostList) {
                int c = 0;
                sheet.addCell(new Label(c, r, post.getPostCode())); c+=1;
                sheet.addCell(new Label(c, r, String.valueOf(post.getPostCapacity()))); c+=1;
                sheet.addCell(new Label(c, r, post.getDateCreated())); c+=1;
                sheet.addCell(new Label(c, r, post.getTimeCreated())); c+=1;

                boolean hasPostLoad = post.getLoad() != null;
//                Toast.makeText(context, post.getPostCode() + "\nhas load:" + hasPostLoad, Toast.LENGTH_LONG).show();
                if (hasPostLoad) {
                    PostLoad pl = post.getLoad();
                    sheet.addCell(new Label(c, r, String.valueOf(pl.getPhaseR()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pl.getPhaseS()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pl.getPhaseT()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pl.getNutralN()))); c+=1;
                }

                boolean hasPostVoltage = post.getVoltage() != null;
//                Toast.makeText(context, post.getPostCode() + "\nhas load:" + hasPostLoad, Toast.LENGTH_LONG).show();
                if (hasPostVoltage) {
                    PostVoltage pv =post.getVoltage();
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getTR()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getST()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getRS()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getRN()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getSN()))); c+=1;
                    sheet.addCell(new Label(c, r, String.valueOf(pv.getST()))); c+=1;
                }
                boolean doesFeedersLoadNotNull = post.getLoadList() != null;
                boolean doesFeedersloadHasValue = false;
                if (doesFeedersLoadNotNull) {
                    doesFeedersloadHasValue = !(post.getLoadList().isEmpty());
//                    Toast.makeText(context, post.getPostCode() + "\nhas feeder laod: " + b3, Toast.LENGTH_LONG).show();
                }
                else {
//                    Toast.makeText(context, post.getPostCode() + "\ngetLoadList is null: " + b2, Toast.LENGTH_LONG).show();
                }

                if (doesFeedersloadHasValue) {
//                    Toast.makeText(context, post.getPostCode() + "\nhas laod: " + b1 + "\nhas feeder laod: " + b3, Toast.LENGTH_LONG).show();

                    List<FeederLoad> feedersLoad = post.getLoadList();
                    int feedersCount = 0;
                    for (FeederLoad load : feedersLoad) {
                        sheet.addCell(new Label(c, r, String.valueOf(load.getPhaseR()))); c+=1;
                        sheet.addCell(new Label(c, r, String.valueOf(load.getPhaseS()))); c+=1;
                        sheet.addCell(new Label(c, r, String.valueOf(load.getPhaseT()))); c+=1;
                        sheet.addCell(new Label(c, r, String.valueOf(load.getNutralN()))); c+=1;
                        feedersCount++;
                    }
                    if (maxFeederCount < feedersCount) {
                        {
                            maxFeederCount = feedersCount;
                        }
                    }
                }
            r+=1;
            }
            setSheetHeader(sheet, maxFeederCount);

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }

    private String getCurrentUsername() {
        return "abbas";
    }

        public void showStorageAlertDialog() {


//        File downLoadLocationFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            Uri downLoadUri = Uri.fromFile(downLoadLocationFile);
////            Toast.makeText(context,downLoadUri.toString() , Toast.LENGTH_SHORT).show();
//
//
//
//
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//        alertBuilder.setTitle("Storage")
//                .setMessage("Storage فعال نیست. آیا مایلید تنظیمات را انجام دهید؟")
//                .setPositiveButton("Setting", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                     //   grantPermission();
//
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        alertBuilder.show();


    }



}

