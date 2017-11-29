package com.searcin.view;

public interface Dto {
	interface ClassRange {
		public static interface id {}
		public static interface name {}
	}
	interface Address {
		public static interface id {}
		public static interface name extends Dto.Area.name {}
		public static interface basic extends name{}
		public static interface location {}
		public static interface detail extends basic, location {}
		public static interface audit extends detail {}
	}
	interface Area {
		public static interface id {}
		public static interface name {}
		public static interface audit extends name {}
	}
	interface Category {
		public static interface id {}
		public static interface name {}
		public static interface audit extends name {}
	}
	interface SubCategory {
		public static interface id {}
		public static interface name {}
		public static interface audit extends name {}
	}
	interface Contact {
		public static interface id {}
		public static interface name {}
		public static interface basic extends name{}
		public static interface detail extends basic {}
		public static interface audit extends detail {}
	}
	interface Service {
		public static interface id {}
		public static interface name {}
		public static interface desc extends name {}
		public static interface audit extends desc {}
	}
	interface Vendor {
		public static interface id {}
		public static interface name {}
		public static interface description {}
		public static interface hierarchy {}
		public static interface services {}
		public static interface classrange {}
		public static interface address{}
		public static interface contact{}
		public static interface audit{}
		public static interface logo {}
		public static interface images {}
	}
	interface Asset {
		public static interface key {}
		public static interface url {}
		public static interface audit extends key, url {}
	}
}
