package io.fabianterhorst.fastlayout.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

/**
 * Created by fabianterhorst on 16.05.16.
 */
public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {

    public StringHolder name;

    public SampleItem withName(String name) {
        this.name = new StringHolder(name);
        return this;
    }

    @Override
    public int getType() {
        return R.id.sample_item;
    }

    @Override
    public int getLayoutRes() {
        return -1;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return null;
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new ViewHolder(new ItemSampleLayout(parent.getContext()));
    }

    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);
        name.applyToOrHide(viewHolder.name);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;

        public ViewHolder(ItemSampleLayout view) {
            super(view);
            this.name = view.getName();
        }
    }
}
