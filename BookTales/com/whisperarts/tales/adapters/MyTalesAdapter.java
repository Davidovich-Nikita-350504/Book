package com.whisperarts.tales.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.whisperarts.library.common.utils.billing.IabHelper;
import com.whisperarts.tales.C0026R;
import com.whisperarts.tales.entities.FullTaleInfo;
import com.whisperarts.tales.entities.FullTaleInfo.PACK;
import java.util.ArrayList;

public class MyTalesAdapter extends ArrayAdapter<FullTaleInfo> {
    private static final String BUY = "\u041a\u0443\u043f\u0438\u0442\u044c";
    private static final String FREE = "\u0411\u0435\u0441\u043f\u043b\u0430\u0442\u043d\u043e";
    private static final String FREE_GET_TEXT = "\u0421\u043a\u0430\u0447\u0430\u0442\u044c";
    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<FullTaleInfo> talesInfo;

    /* renamed from: com.whisperarts.tales.adapters.MyTalesAdapter.1 */
    class C00271 implements OnTouchListener {
        private final /* synthetic */ FullTaleInfo val$tale;

        C00271(FullTaleInfo fullTaleInfo) {
            this.val$tale = fullTaleInfo;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case IabHelper.BILLING_RESPONSE_RESULT_USER_CANCELED /*1*/:
                    Intent intent;
                    try {
                        intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("market://details?id=" + this.val$tale.getFullPackage()));
                        MyTalesAdapter.this.context.startActivity(intent);
                        break;
                    } catch (ActivityNotFoundException e) {
                        intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + this.val$tale.getFullPackage()));
                        MyTalesAdapter.this.context.startActivity(intent);
                        break;
                    }
            }
            return true;
        }
    }

    static class ViewHolder {
        public TextView buyButton;
        public View buyLayout;
        public TextView description;
        public View frame;
        public ImageView icon;
        public TextView price;
        public View rootLayout;
        public TextView title;

        ViewHolder() {
        }
    }

    public MyTalesAdapter(Context context, ArrayList<FullTaleInfo> talesInfo) {
        super(context, C0026R.layout.my_tales_row, talesInfo);
        this.context = context;
        this.talesInfo = talesInfo;
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View row = convertView;
        FullTaleInfo tale = (FullTaleInfo) this.talesInfo.get(position);
        if (row == null) {
            row = this.inflater.inflate(C0026R.layout.my_tales_row, parent, false);
            holder = new ViewHolder();
            holder.icon = (ImageView) row.findViewById(C0026R.id.icon);
            holder.title = (TextView) row.findViewById(C0026R.id.title);
            holder.description = (TextView) row.findViewById(C0026R.id.description);
            holder.frame = row.findViewById(C0026R.id.frame);
            holder.price = (TextView) row.findViewById(C0026R.id.price);
            holder.buyButton = (TextView) row.findViewById(C0026R.id.buy_button);
            holder.buyLayout = row.findViewById(C0026R.id.buy_button_layout);
            holder.rootLayout = row.findViewById(C0026R.id.root);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.icon.setImageBitmap(tale.getImage());
        holder.title.setText(tale.getName());
        holder.description.setText(tale.getDescription());
        if (tale.isPaid()) {
            holder.title.setTextColor(this.context.getResources().getColor(C0026R.color.my_tales_name));
            holder.description.setTextColor(this.context.getResources().getColor(C0026R.color.my_tales_name));
            holder.frame.setBackgroundResource(C0026R.drawable.row_shape);
            holder.frame.setPadding(this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_2), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_3), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_2), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_3));
            holder.rootLayout.setPadding(this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_3), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_1), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_3), this.context.getResources().getDimensionPixelSize(C0026R.dimen.padding_1));
            holder.buyLayout.setVisibility(8);
        } else {
            holder.description.setTextColor(this.context.getResources().getColor(C0026R.color.catalog_description_color));
            if (PACK.packs.containsKey(tale.getFullPackage())) {
                holder.title.setTextColor(-1);
            } else {
                holder.title.setTextColor(this.context.getResources().getColor(C0026R.color.catalog_title_color));
            }
            holder.frame.setBackgroundDrawable(null);
            holder.frame.setPadding(0, 0, 0, 0);
            holder.rootLayout.setPadding(0, 0, 7, 0);
            holder.buyLayout.setVisibility(0);
            addButtonListener(holder.buyButton, tale);
            if (tale.getPrice().equals("0")) {
                holder.price.setText(FREE);
                holder.buyButton.setText(FREE_GET_TEXT);
                holder.buyButton.setBackgroundDrawable(this.context.getResources().getDrawable(C0026R.drawable.back_free));
            } else {
                holder.price.setText(tale.getPrice() + " \u0443.\u0435.");
                holder.buyButton.setText(BUY);
                holder.buyButton.setBackgroundDrawable(this.context.getResources().getDrawable(C0026R.drawable.back_paid));
            }
        }
        return row;
    }

    private void addButtonListener(TextView button, FullTaleInfo tale) {
        button.setOnTouchListener(new C00271(tale));
    }
}
