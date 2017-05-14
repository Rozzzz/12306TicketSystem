package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.ticketOrderInfoDBH;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Refund {

	protected Shell shell;
    private static String info;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Refund window = new Refund();
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
		shell.setImage(SWTResourceManager.getImage(Refund.class, "/image/12306_logo.jpg"));
		shell.setSize(450, 300);
		shell.setText("退票窗口");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(66, 188, 85, 33);
		btnNewButton.setText("退票");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_1.setBounds(274, 188, 80, 33);
		btnNewButton_1.setText("取消");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setBounds(151, 56, 101, 27);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(43, 80, 85, 20);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(151, 89, 101, 2);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(274, 80, 88, 20);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(139, 135, 157, 27);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(161, 99, 91, 17);

		final String []strArr=info.split(",");
		lblNewLabel.setText(strArr[7]);
		lblNewLabel_1.setText(strArr[2]);
		label_1.setText(strArr[3]);
		label_2.setText(strArr[10]+"元");
		lblNewLabel_2.setText("座位号为："+strArr[9]+"号");
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ticketOrderInfoDBH ticketorderInfoDBH=new ticketOrderInfoDBH();
				if(ticketorderInfoDBH.alter(strArr[0], "已退票")>0){
					MessageDialog.openInformation(shell, "成功提示", "您已退票成功");
					int a=Order.t++;
					Order.text.setText(String.valueOf(a));
					shell.close();
				}
			}
		});
	}
}
