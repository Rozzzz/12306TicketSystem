package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Label;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class DeleteStation {

	protected Shell shell;
	private static String s_ID;
	private String start_time;
	private String end_time;
	private String ss="当前有";
	private List<String> list9=new ArrayList<String>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DeleteStation window = new DeleteStation();
			window.open(s_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(String s_ID) {
		this.s_ID=s_ID;
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.APPLICATION_MODAL|SWT.CLOSE);
		shell.setSize(676, 437);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		final Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblNewLabel.setBounds(225, 227, 315, 25);
		
		final Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label.setBounds(38, 181, 612, 25);
		
		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(105, 92, 147, 25);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(286, 104, 64, 2);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(28, 61, 154, 25);
		lblNewLabel_1.setText("请选择车站停运时间");
		
		final Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(124, 304, 80, 27);
		btnNewButton.setText("确定");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(413, 304, 80, 27);
		btnNewButton_1.setText("取消");
		
		final CalendarCombo calendarCombo_2 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_2.setBounds(382, 92, 158, 25);

		 final DBHelper db=new DBHelper();
		String sql="select s_name from station where s_ID=?";
		List<Object> params=new ArrayList<Object>();
        params.add(s_ID);
        System.out.println(db.find(sql, params).get("s_name")+s_ID);
         final String s_name=db.find(sql, params).get("s_name");
		String sql1="select DISTINCT t_ID from traininfo where t_init=? or t_dest=?";
		List<Object> params1=new ArrayList<Object>();
		params1.add(s_name);
		params1.add(s_name);
		final List<String> list3=new ArrayList<String>();
		List<Map<String,String>> list1=db.finds(sql1, params1);
		System.out.println(list1);
		for(int i=0,len=list1.size();i<len;i++){
			System.out.println(list1.get(i).get("t_id"));
			list3.add(list1.get(i).get("t_id"));
		}
		
		final List<String> list2=new ArrayList<String>();
		final String sql2="select DISTINCT t_ID from train where ? BETWEEN start_day and end_day or ? BETWEEN start_day and end_day";
		calendarCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				start_time=calendarCombo.getDateAsString().trim();
				System.out.println(start_time+"------");
				if(end_time!=null&&!"".equals(end_time.trim())){
					System.out.println("---------");
					System.out.println(start_time);
					System.out.println(end_time);
					
					ss="当前有";
					List<Object> params=new ArrayList<Object>();
                    params.add(start_time);
                    params.add(end_time);
                    List<Map<String,String>> list=db.finds(sql2, params);
                	for(int i=0,len=list.size();i<len;i++){
            			list2.add(list.get(i).get("t_id"));
            		}          
                	for(int i=0,len=list2.size();i<len;i++){
    					for(int j=0,len1=list3.size();j<len1;j++){
    						if((list3.get(j).trim()).equals(list2.get(i).trim())){
    							list9.add(list2.get(i));
    							ss+=list2.get(i)+",";
    						}
    					}
    				}
    				ss+="通过"+s_name+"如果停止该段时间内，这些火车将暂停服务";
    				label.setText(ss);
    				lblNewLabel.setText("是否确认停止该站点的服务？");
    				btnNewButton.setEnabled(true);
				}
			}
		});
		calendarCombo_2.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				end_time=calendarCombo_2.getDateAsString().trim();
				System.out.println(end_time+"*******");
				if(start_time!=null&&!"".equals(start_time.trim())){
					ss="当前有";
//					System.out.println("***************");
//					System.out.println(start_time);
//					System.out.println(end_time);
					List<Object> params=new ArrayList<Object>();
                    params.add(start_time);
                    params.add(end_time);
                    List<Map<String,String>> list=db.finds(sql2, params);
                	for(int i=0,len=list.size();i<len;i++){
            			list2.add(list.get(i).get("t_id"));
            		}            
                	for(int i=0,len=list2.size();i<len;i++){
    					for(int j=0,len1=list3.size();j<len1;j++){
    						if((list3.get(j).trim()).equals(list2.get(i).trim())){
    							list9.add(list2.get(i));
    							ss+=list2.get(i)+",";
    						}
    					}
    				}
    				ss+="通过"+s_name+"如果停止该段时间内，这些火车将暂停服务";
    				label.setText(ss);
    				lblNewLabel.setText("是否确认停止该站点的服务？");
    				btnNewButton.setEnabled(true);
				}
			}
		});
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(list9.size()==0||list9==null){
					String t=s_ID;
					TrainManage.text.setText(String.valueOf(t));
				}
				DBHelper db=new DBHelper();
				String sql="INSERT INTO s_daily_report (times, s_ID, a_ID, s_name, defect,start_dates,end_dates)VALUES(to_char(SYSDATE,'YYYY-MM-DD'),?,?,?,'站点暂停使用',?,?)";
				List<Object> params=new ArrayList<Object>();
				params.add(s_ID);
                params.add(UiCollections.currentLoginUser.get("a_id"));
                params.add(s_name);
                params.add(start_time);
                params.add(end_time);
//                System.out.println("-------------------");
//                System.out.println(s_ID);
//                System.out.println(UiCollections.currentLoginUser.get("a_id"));
//                System.out.println(s_name);
//                System.out.println("====================");
//                System.out.println(db.update(sql, params));
                if(db.update(sql, params)>0){
  //              	System.out.println(list9.size()+"----88888");
                	for(int i=0,len3=list9.size();i<len3;i++){
                		List<Object> par2=new ArrayList<Object>();
                		par2.add(list9.get(i));
                		String sql1="insert into t_daily_report(times, a_ID, t_ID, start_dates, end_dates,note, defect)values(to_char(SYSDATE,'YYYY-MM-DD'), ?, ?, ?, ?,?,'因故停运')";
                		List<Object> params1=new ArrayList<Object>();
                		params1.add(UiCollections.currentLoginUser.get("a_id"));
                		String sql5="select start_day, end_day from train where t_ID=?";
                		List<Object> params6=new ArrayList<Object>();
                		params6.add(list9.get(i).toString());
                		params1.add(list9.get(i).toString());
                		String start_day=db.find(sql5, params6).get("start_day");
                		String end_day=db.find(sql5, params6).get("end_day");
                		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                     // System.out.println("start_day"+start_day);
                		Date start_day1=new Date();
                		Date start_time1=new Date();
                		Date end_day1=new Date();
                		Date end_time1=new Date();
                		try {
                			start_day1 = sdf.parse(start_day);
                			start_time1=sdf.parse(start_time);
                			end_day1=sdf.parse(end_day);
                			end_time1=sdf.parse(end_time);
                		} catch (ParseException e1) {
                			e1.printStackTrace();
                		}
//    				System.out.println("end_day"+end_day);
//    				System.out.println(start_day1.compareTo(start_time1)>=0);
//    				System.out.println(end_day1.compareTo(end_time1)<=0);
                		//List<Object> par=new ArrayList<Object>();
                		if(start_day1.compareTo(start_time1)>=0&&end_day1.compareTo(end_time1)<=0){
                			params1.add(start_day);
                			//	par.add(end_day);
                			params1.add(end_day);
                			//	par.add(start_day);
                			par2.add(start_day);
                			par2.add(end_day);
                			params1.add(s_name+"车站暂停使用");
                		}else if(start_day1.compareTo(start_time1)>=0&&end_day1.compareTo(end_time1)>0&&start_day1.compareTo(end_time1)<=0){
                			params1.add(start_day);
                			params1.add(end_time);
//    					par.add(end_day);
//    					par.add(start_day);
                			par2.add(start_day);
                			par2.add(end_day);
                			params1.add(s_name+"车站暂停使用");
                		}else if(start_day1.compareTo(start_time1)<=0&&end_day1.compareTo(start_time1)>=0&&end_day1.compareTo(end_time1)<=0){
                			params1.add(start_time);
                			params1.add(end_day);
//    					par.add(end_day);
//    					par.add(start_day);
                			par2.add(start_day);
                			par2.add(end_day);
                			params1.add(s_name+"车站暂停使用");
                		}else if(start_day1.compareTo(start_time1)<=0&&end_day1.compareTo(start_time1)>=0&&end_day1.compareTo(end_time1)>=0){
                			params1.add(start_time);
                			params1.add(end_time);
//    					par.add(end_day);
//    					par.add(start_day);
                			par2.add(start_day);
                			par2.add(end_day);
                			params1.add(s_name+"车站暂停使用");
                		}else{
                			MessageDialog.openError(shell, "失败提示", "找不到火车修改时间");
                		}
                		if(db.update(sql1, params1)>0){
//    					String sq="select round(to_number(to_date(?,'YYYY-MM-DD')-to_date(?,'YYYY-MM-DD')) )FROM dual";
//    					double len1 =db.getTotal(sq, par);
                			String sq1="update ticketOrder SET note='该天火车因故取消，我们将全额退款',condition='已退票' WHERE t_ID=? and datet BETWEEN ? and ?";
                			db.update(sq1, par2);
//    					if(db.update(sq1, par2)==0){
//    						MessageDialog.openError(shell, "失败提示", list9.get(i)+"退票失败，请手动退票");
//    					}
                			MessageDialog.openInformation(shell, "成功提示", list9.get(i)+"修改成功");
                			String a=s_ID;
                			TrainManage.text.setText(String.valueOf(a));
                		}else{
                			MessageDialog.openError(shell, "失败提示", list9.get(i)+"修改失败");
                		}
                	}
					shell.close();
                }
                
			}
		});
	}

	protected Date parse(String start_day) {
		// TODO Auto-generated method stub
		return null;
	}
}
