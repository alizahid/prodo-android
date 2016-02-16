package io.prodo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.prodo.R;

public class StatusAdapter extends ArrayAdapter<StatusAdapter.Item> {

	public StatusAdapter(Context context) {
		super(context, R.layout.badge_status);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.badge_status, parent, false);
		}

		Item item = getItem(position);

		TextView keyView = (TextView) convertView.findViewById(R.id.key);
		TextView valueView = (TextView) convertView.findViewById(R.id.value);

		keyView.setText(item.key);
		valueView.setText(item.value);

		return convertView;
	}

	public void add(String key, String value) {
		Item item = new Item(key, value);

		add(item);
	}

	public void set(String key, String value) {
		for (int index = 0; index < getCount(); index++) {
			Item item = getItem(index);

			if (item.key.equals(key)) {
				item.value = value;

				notifyDataSetChanged();

				break;
			}
		}
	}

	public class Item {
		public String key;
		public String value;

		public Item(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
