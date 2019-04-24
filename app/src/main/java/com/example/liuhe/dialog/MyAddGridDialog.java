package com.example.liuhe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liuhe.R;
import com.example.liuhe.dao.BookMarkDAOFactory;


public class MyAddGridDialog extends Dialog {
    private OnMyDialogListener onListener;
    private int width;
    private TextView dialog_drid_tvname;
    private EditText dialog_drid_edname;
    private TextView dialog_drid_tvurl;
    private EditText dialog_drid_edurl;
    private Button dialog_drid_btnok;
    private Button dialog_drid_btnno;

    //创建一个接口//里面一个函数方法用于回调//该接口可以通过构造函数传进来
    public interface OnMyDialogListener{
        public void back();
    }

    public MyAddGridDialog(Context context, int widthr,OnMyDialogListener onListener) {
        super(context);
        this.onListener = onListener;
        this.width = widthr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_grid);
        Window dialogWindow = this.getWindow();
        //居中显示
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (width-(width*0.1));
        initView();
        setListent();
    }

    private void setListent() {
        dialog_drid_btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookMarkDAOFactory.getBookMark().doCreate(dialog_drid_edname.getText().toString().trim(),dialog_drid_edurl.getText().toString().trim());
                onListener.back();
                dismiss();
            }
        });
        dialog_drid_btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss关闭对话框
                dismiss();
            }
        });
    }

    private void initView() {
        dialog_drid_tvname = (TextView) findViewById(R.id.dialog_drid_tvname);
        dialog_drid_edname = (EditText) findViewById(R.id.dialog_drid_edname);
        dialog_drid_tvurl = (TextView)findViewById(R.id.dialog_drid_tvurl);
        dialog_drid_edurl = (EditText)findViewById(R.id.dialog_drid_edurl);
        dialog_drid_btnok = (Button) findViewById(R.id.dialog_drid_btnok);
        dialog_drid_btnno = (Button)findViewById(R.id.dialog_drid_btnno);
    }
}
