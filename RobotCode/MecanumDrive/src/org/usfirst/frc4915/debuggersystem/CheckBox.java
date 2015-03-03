package org.usfirst.frc4915.debuggersystem;

//public class CheckBox extends AbstractValueWidget {

//    public static final DataType[] TYPES = {DataType.BOOLEAN};

//    public final BooleanProperty editable = new BooleanProperty(this, "Editable", true);

//    private EditableBooleanValueCheckBox valueField;

//    public void init() {
//        setResizable(false);

//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

//        valueField = new EditableBooleanValueCheckBox(getFieldName());

//        add(valueField);
//    }

//    @Override
//    public void propertyChanged(Property property) {
//        if (property == editable) {
//            valueField.setEnabled(editable.getValue());
//        }
//    }
//}