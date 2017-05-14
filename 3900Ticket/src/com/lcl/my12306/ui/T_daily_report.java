package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class T_daily_report {

	protected Shell shell;
	private Text text;
	private static String info;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			T_daily_report window = new T_daily_report();
			window.open(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(String info) {
		this.info=info;
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(T_daily_report.class, "/image/12306_logo.jpg"));
		shell.setSize(616, 370);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(T_daily_report.class, "/image/b15.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label.setBounds(79, 30, 417, 25);
		label.setText("该天火车将改为因故停运，并退还旅客全部金额");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(67, 84, 441, 174);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("请在下框内填入更改原因");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_1.setBounds(148, 61, 256, 25);
		
		final Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(102, 283, 80, 27);
		btnNewButton.setText("提交");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(378, 283, 80, 27);
		btnNewButton_1.setText("取消");
		
		final String []strArr=info.split(",");
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				btnNewButton.setEnabled(true);
			}
		});

		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DBHelper db=new DBHelper();
				String sql="insert into t_daily_report(times, a_ID, t_ID, start_dates, note, defect)values(to_char(SYSDATE,'YYYY-MM-DD'), ?, ?, ?, ?,'因故停运')";
				List<Object> params=new ArrayList<Object>();
				String note=text.getText();
                params.add(UiCollections.currentLoginUser.get("a_id"));
                params.add(strArr[0]);
                params.add(strArr[2]);
                params.add(note);
                System.out.println(strArr[0]+"--"+strArr[2]+"--"+note);
                if(db.update(sql, params)>0){
                	String sql10="UPDATE ticketOrder SET note='该天火车因故取消，我们将全额退款',condition='已退票' WHERE t_ID=? AND datet=?";
    				List<Object> params10=new ArrayList<Object>();
    				params10.add(strArr[0]);
    				params10.add(strArr[2]);
					MessageDialog.openInformation(shell, "成功提示", "修改成功，已记录在册");
    				if(db.update(sql10, params10)<=0){
    					MessageDialog.openError(shell, "失败提示", "该天火车退票失败，请手动取消");
    				}
					shell.close();
                }else{
					MessageDialog.openError(shell, "失败提示", "修改失败");
                }
			}
		});
	}
}
