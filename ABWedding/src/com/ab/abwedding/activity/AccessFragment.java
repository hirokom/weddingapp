package com.ab.abwedding.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ab.abwedding.R;
import com.ab.abwedding.base.FragmentBase;
import com.ab.abwedding.data.CommonData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AccessFragment extends FragmentBase {

	private static AccessFragment accssDtlFragment;

	// TODO 2回目の起動で1回目の描画とバッティングする？のでエラーを回避するためViewを使いまわす。
	// private static View rootView;

	/**
	 * get AccessDetailFragment instance
	 * 
	 * @param args
	 * @return
	 */
	public static AccessFragment getFragment(Bundle args) {
		if (accssDtlFragment != null) {
			accssDtlFragment.setArguments(args);
		} else {
			accssDtlFragment = new AccessFragment();
			accssDtlFragment.setArguments(args);
		}
		return accssDtlFragment;
	}

	// GoogleMap
	private GoogleMap googleMap;

	protected void subOnCreate(Bundle savedInstanceState) {

	}

	private static final LatLng TOKYO = new LatLng(35.681382, 139.766084);

	protected View subOnCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_access_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText(mItem.content);
		}

		// set address to TextView
		((TextView) rootView.findViewById(R.id.txtVwPostalCode))
				.setText(CommonData.getPostalCode());
		((TextView) rootView.findViewById(R.id.txtVwAdress)).setText(CommonData
				.getAddress());

		FragmentManager fm = getActivity().getFragmentManager();
		MapFragment mf = (MapFragment) fm.findFragmentById(R.id.map);
		googleMap = mf.getMap();
		if (googleMap != null) {
			// マップをハイブリッド表示にする
			googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

			// 屋内マップ表示を無効にする（標準は true）
			googleMap.setIndoorEnabled(false);

			// 現在地表示ボタンを有効にする
			googleMap.setMyLocationEnabled(true);
			// UiSettings にボタン表示設定があるが標準は true なので設定不要
			// mMap.getUiSettings().setMyLocationButtonEnabled(true);

			// 東京駅にマーカーをつける
			googleMap.addMarker(new MarkerOptions()
					.position(TOKYO)
					.title("東京駅")
					.snippet("2012年10月1日に復元工事が完了")
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

			// カメラの位置を東京駅に変える
			moveCameraToTokyo(false);

			// 地図の長押しでカメラを東京駅まで移動する
			googleMap
					.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
						@Override
						public void onMapLongClick(LatLng point) {
							moveCameraToTokyo(true);
						}
					});
		}

		return rootView;
	}

	/**
	 * カメラを東京駅に移動する
	 * 
	 * @param isAnimation
	 *            アニメーション移動するかの判定。true でアニメーション移動。
	 */
	private void moveCameraToTokyo(boolean isAnimation) {
		// カメラの位置情報を作成する
		CameraUpdate camera = CameraUpdateFactory
				.newCameraPosition(new CameraPosition.Builder().target(TOKYO)
						.zoom(18.0f).build());
		if (isAnimation) {
			// アニメーション移動する
			googleMap.animateCamera(camera);
		} else {
			// 瞬間移動する
			googleMap.moveCamera(camera);
		}
	}
}
