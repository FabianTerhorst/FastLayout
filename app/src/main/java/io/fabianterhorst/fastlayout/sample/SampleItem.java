package io.fabianterhorst.fastlayout.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

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
    public View generateView(Context ctx) {
        RecyclerView.ViewHolder viewHolder = getViewHolder(LayoutCache.getInstance(ctx).getLayout(LayoutCache.Item_Sample_Layout));
        bindView((SampleItem.ViewHolder) viewHolder);
        return viewHolder.itemView;
    }

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);
        name.applyToOrHide(viewHolder.name);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
        }
    }
}
