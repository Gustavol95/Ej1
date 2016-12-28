package com.ej1.iedeveloper.ej1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.ej1.iedeveloper.ej1.R;
import com.ej1.iedeveloper.ej1.model.Hijo;
import com.ej1.iedeveloper.ej1.model.HijoViewHolder;
import com.ej1.iedeveloper.ej1.model.Padre;
import com.ej1.iedeveloper.ej1.model.PadreViewHolder;

import java.util.List;

/**
 * Created by iedeveloper on 28/12/16.
 */

public class ExpandableAdapter extends ExpandableRecyclerAdapter<PadreViewHolder,HijoViewHolder> {
    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */


    public ExpandableAdapter(@NonNull List parentItemList) {
        super(parentItemList);
    }

    private LayoutInflater mInflator;

    public ExpandableAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public PadreViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view=mInflator.inflate(R.layout.recycler_padre,parentViewGroup,false);
        return new PadreViewHolder(view);
    }

    @Override
    public HijoViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view=mInflator.inflate(R.layout.recycler_hijo,childViewGroup,false);
        return new HijoViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(PadreViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Padre padre=(Padre)parentListItem;
        parentViewHolder.bind(padre);
    }

    @Override
    public void onBindChildViewHolder(HijoViewHolder childViewHolder, int position, Object childListItem) {
        Hijo hijo=(Hijo)childListItem;
        childViewHolder.bind(hijo);
    }

}
