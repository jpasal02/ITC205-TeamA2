package datamanagement;

import java.util.List;

import org.jdom.*;

public class UnitManager {

	private static UnitManager self = null;

	private UnitMap unitMap_;

	public static UnitManager unitManager() {
		if (self == null)
			self = new UnitManager();
		return self;
	}

	private UnitManager() {
		unitMap_ = new UnitMap();
	}

	public IUnit getUnit(String unitCreate) {
		IUnit iUnit = unitMap_.get(unitCreate);
		return iUnit != null ? iUnit : createUnit(unitCreate);

	}

	@SuppressWarnings("unchecked")
	private IUnit createUnit(String unitCode) {

		IUnit iUnit;

		for (Element element : (List<Element>) XMLManager.getXML()
				.getDocument().getRootElement().getChild("unitTable")
				.getChildren("unit"))
			if (unitCode.equals(element.getAttributeValue("uid"))) {
				StudentUnitRecordList studentUnitlist;

				studentUnitlist = null;
				iUnit = new Unit(element.getAttributeValue("uid"),
						element.getAttributeValue("name"), Float.valueOf(
								element.getAttributeValue("ps")).floatValue(),
						Float.valueOf(element.getAttributeValue("cr"))
								.floatValue(), Float.valueOf(
								element.getAttributeValue("di")).floatValue(),
						Float.valueOf(element.getAttributeValue("hd"))
								.floatValue(), Float.valueOf(
								element.getAttributeValue("ae")).floatValue(),
						Integer.valueOf(element.getAttributeValue("asg1wgt"))
								.intValue(), Integer.valueOf(
								element.getAttributeValue("asg2wgt"))
								.intValue(), Integer.valueOf(
								element.getAttributeValue("examwgt"))
								.intValue(), StudentUnitRecordManager
								.instance().getRecordsByUnit(unitCode));
				unitMap_.put(iUnit.getUnitCode(), iUnit);
				return iUnit;
			}

		throw new RuntimeException("DBMD: createUnit : unit not in file");
	}

	@SuppressWarnings("unchecked")
	public UnitMap getUnits() {

		UnitMap unitMap;
		IUnit iUnit;

		unitMap = new UnitMap();
		for (Element element : (List<Element>) XMLManager.getXML()
				.getDocument().getRootElement().getChild("unitTable")
				.getChildren("unit")) {
			iUnit = new UnitProxy(element.getAttributeValue("uid"),
					element.getAttributeValue("name"));
			unitMap.put(iUnit.getUnitCode(), iUnit);
		} // unit maps are filled with PROXY units
		return unitMap;
	}

}
