package pl.wasat.smarthma.ui.frags.base;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.EntryImagesListAdapter;
import pl.wasat.smarthma.database.EoDbAdapter;
import pl.wasat.smarthma.model.FedeoRequest;
import pl.wasat.smarthma.model.eo.Pos;
import pl.wasat.smarthma.model.feed.Entry;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.utils.rss.FedeoSearchRequest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link BaseShowProductsListFragment.OnBaseShowProductsListFragmentListener}
 * interface to handle interaction events. Use the
 * {@link BaseShowProductsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class BaseShowProductsListFragment extends BaseSpiceFragment {
	private static final String KEY_PARAM_FEDEO_REQUEST = "pl.wasat.samrthma.KEY_PARAM_FEDEO_REQUEST";

	private FedeoRequest fedeoRequest;

	private OnBaseShowProductsListFragmentListener mListener;

	private ListView entryImagesListView;
	private View loadingView;

	private EntryImagesListAdapter entryImagesListAdapter;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param bounds
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment SearchProductsFeedsFragment.
	 */
	public static BaseShowProductsListFragment newInstance(
			FedeoRequest fedeoRequest) {
		BaseShowProductsListFragment fragment = new BaseShowProductsListFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_PARAM_FEDEO_REQUEST, fedeoRequest);
		fragment.setArguments(args);
		return fragment;
	}

	public BaseShowProductsListFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			fedeoRequest = (FedeoRequest) getArguments().getSerializable(
					KEY_PARAM_FEDEO_REQUEST);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_collections_group_list,
				container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}

		entryImagesListView = (ListView) getView().findViewById(
				R.id.listview_collections_group);
		loadingView = getView().findViewById(R.id.loading_layout);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnBaseShowProductsListFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSearchProductsFeedFragmentListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onStart() {
		super.onStart();
		// TODO: Find solution - why fragment is called twice
		if (fedeoRequest != null) {
			loadSearchProductsFeedResponse(fedeoRequest);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		entryImagesListView
				.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	public void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			entryImagesListView.setItemChecked(mActivatedPosition, false);
		} else {
			entryImagesListView.setItemChecked(position, true);
		}
		mActivatedPosition = position;
	}

	private void updateShowProductsListViewContent(final List<Entry> entryList) {
		if (entryList.isEmpty()) {
			getView().setVisibility(View.GONE);
			loadFailureFrag();
			
		} else {
			for (Entry a : entryList) {
				EoDbAdapter dba = new EoDbAdapter(getActivity());
				dba.openToRead();
				Entry fetchedSearch = dba.getBlogListing(a.getGuid());
				dba.close();
				if (fetchedSearch == null) {
					dba = new EoDbAdapter(getActivity());
					dba.openToWrite();
					dba.insertBlogListing(a.getGuid());
					dba.close();
				} else {
					a.setDbId(fetchedSearch.getDbId());
					a.setOffline(fetchedSearch.isOffline());
					a.setRead(fetchedSearch.isRead());
				}
			}

			entryImagesListAdapter = new EntryImagesListAdapter(getActivity()
					.getBaseContext(), getBitmapSpiceManager(), entryList);
			entryImagesListView.setAdapter(entryImagesListAdapter);

			loadingView.setVisibility(View.GONE);
			entryImagesListAdapter.notifyDataSetChanged();
			entryImagesListView.setVisibility(View.VISIBLE);

			// Click event for single list row
			entryImagesListView
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							loadProductItemDetails(entryList.get(position));
						}
					});
		}
	}

	public void loadFailureFrag() {
	}

	public void loadProductItemDetails(Entry entry) {
	}

	/**
	 * @param searchProductFeeds
	 */
	public void loadSearchResultProductsIntroDetailsFrag(
			Feed searchProductFeeds) {
	}

	/**
	 * 
	 */
	private void loadSearchProductsFeedResponse(FedeoRequest fedeoRequest) {
		if (fedeoRequest != null) {
			getActivity().setProgressBarIndeterminateVisibility(true);

			getSpiceManager().execute(new FedeoSearchRequest(fedeoRequest),
					this);
		}
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnBaseShowProductsListFragmentListener {
		// TODO: Update argument type and name
		public void onBaseShowProductsListFragmentItemSelected(String id);

		public void onBaseShowProductsListFragmentFootprintSend(
				ArrayList<List<Pos>> footPrints);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.octo.android.robospice.request.listener.RequestListener#onRequestSuccess
	 * (java.lang.Object)
	 */
	@Override
	public void onRequestSuccess(Feed searchProductFeeds) {
		getActivity().setProgressBarIndeterminateVisibility(false);
		Toast.makeText(getActivity(), R.string.ok_, Toast.LENGTH_SHORT).show();
		if (searchProductFeeds == null) {
			searchProductFeeds = new Feed();
		}
		updateShowProductsListViewContent(searchProductFeeds.getEntries());
		loadSearchResultProductsIntroDetailsFrag(searchProductFeeds);
		ArrayList<List<Pos>> footPrints = getFootprints(searchProductFeeds
				.getEntries());
		mListener.onBaseShowProductsListFragmentFootprintSend(footPrints);

	}

	/**
	 * @param searchProductFeeds
	 * @return
	 */
	private ArrayList<List<Pos>> getFootprints(List<Entry> searchProductFeeds) {
		ArrayList<List<Pos>> footPrintsArr = new ArrayList<List<Pos>>();
		for (Entry searchProductFeed : searchProductFeeds) {
			if (searchProductFeed.getEarthObservation() != null) {
				footPrintsArr.add(searchProductFeed.getEarthObservation()
						.getFeatureOfInterest().getFootprint()
						.getMultiExtentOf().getMultiSurface()
						.getSurfaceMembers().getPolygon().getExterior()
						.getLinearRing().getPosList());
			}
		}
		return footPrintsArr;
	}

}
