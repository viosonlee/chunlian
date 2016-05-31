package com.vioson.chunlian.fragments;



import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vioson.chunlian.R;


public class AboutFragment extends Fragment implements OnClickListener{
	private Button btn_login,btn_logoff,btn_help,btn_visitus,btn_about;
	private String url="http://weibo.com/viosonlee";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.text_animation3, container, false);
		btn_logoff=(Button) view.findViewById(R.id.btn_set_logoff);
		btn_login=(Button) view.findViewById(R.id.btn_set_login);
		btn_help=(Button) view.findViewById(R.id.btn_set_help);
		btn_visitus=(Button) view.findViewById(R.id.btn_set_visitus);
		btn_about=(Button) view.findViewById(R.id.btn_set_about);
		btn_logoff.setOnClickListener(this);
		btn_help.setOnClickListener(this);
		btn_visitus.setOnClickListener(this);
		btn_about.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_set_visitus:
			Uri uri=Uri.parse(url);
			Intent intent =new Intent(Intent.ACTION_VIEW,uri);
			startActivity(intent);
			break;
		case R.id.btn_set_help:
			Toast.makeText(getActivity(), getString(R.string.no_update), Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_set_about:
			showUs();
			break;
		case R.id.btn_set_logoff:
			getActivity().finish();
			break;
		case R.id.btn_set_login:
			
			break;
		}		
	}

	private void showUs() {
		AlertDialog.Builder builder=new Builder(getActivity());
		builder.setTitle(getString(R.string.about_title));
		builder.setMessage(R.string.about);
		builder.setPositiveButton(getString(R.string.i_konw), new  DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 dialog.dismiss();
			}
		});
		builder.create().show();
	}



}
