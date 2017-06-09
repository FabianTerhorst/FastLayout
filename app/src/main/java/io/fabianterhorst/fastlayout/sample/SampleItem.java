package io.fabianterhorst.fastlayout.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;
import com.mikepenz.materialize.holder.StringHolder;

/**
 * Created by fabianterhorst on 16.05.16.
 */
public class SampleItem extends AbstractItem<SampleItem, SampleItem.ViewHolder> {

    private static final ViewHolderFactory<? extends ViewHolder> FACTORY = new ItemFactory();

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

   /* @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return new ViewHolder(new ItemSampleLayout(parent.getContext()));
    }*/

    @Override
    public void bindView(ViewHolder viewHolder) {
        super.bindView(viewHolder);
        name.applyToOrHide(viewHolder.name);
    }

    protected static class ItemFactory implements ViewHolderFactory<ViewHolder> {
        public ViewHolder create(View v) {
            return new ViewHolder(new ItemSampleLayout(v.getContext()));
        }
    }

    @Override
    public ViewHolderFactory<? extends ViewHolder> getFactory() {
        return FACTORY;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;

        public ViewHolder(ItemSampleLayout view) {
            super(view);
            this.name = view.getName();
        }
    }
}
