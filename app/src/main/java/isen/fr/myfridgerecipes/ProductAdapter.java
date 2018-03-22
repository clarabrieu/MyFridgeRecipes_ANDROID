package isen.fr.myfridgerecipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Philippe on 21/03/2018.
 */



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private List<Product> stList;

    public ProductAdapter(List<Product> students) {
        this.stList = students;

    }

    // Create new views
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.product_list_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.tvName.setText(stList.get(position).getName());

        //viewHolder.tvEmailId.setText(stList.get(position).getEmailId());

        viewHolder.chkSelected.setChecked(stList.get(position).isSelected());

        viewHolder.chkSelected.setTag(stList.get(position));


        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Product contact = (Product) cb.getTag();

                contact.setSelected(cb.isChecked());
                stList.get(pos).setSelected(cb.isChecked());


            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return stList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvEmailId;

        public CheckBox chkSelected;

        public Product singlestudent;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);


            chkSelected = (CheckBox) itemLayoutView
                    .findViewById(R.id.chkSelected);

        }

    }

    // method to access in activity after updating selection
    public List<Product> getStudentist() {
        return stList;
    }

}
