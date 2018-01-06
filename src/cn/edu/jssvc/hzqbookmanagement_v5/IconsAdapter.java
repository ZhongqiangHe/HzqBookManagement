package cn.edu.jssvc.hzqbookmanagement_v5;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconsAdapter extends ArrayAdapter<Icons> {

	private int resourceId;

	public IconsAdapter(Context context, int textViewResourceId,
			List<Icons> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Icons icons = getItem(position); 
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView iconsImage = (ImageView) view.findViewById(R.id.icons_image);
		TextView iconsName = (TextView) view.findViewById(R.id.icons_name);
		iconsImage.setImageResource(icons.getImageId());
		iconsName.setText(icons.getName());
		iconsName.setTextSize(17);
		return view;
	}

}

