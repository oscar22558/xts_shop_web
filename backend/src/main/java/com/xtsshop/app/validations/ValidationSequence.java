package com.xtsshop.app.validations;


import com.xtsshop.app.validations.groups.ColumnEmpty;
import com.xtsshop.app.validations.groups.ColumnLength;
import com.xtsshop.app.validations.groups.ColumnPattern;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

//@GroupSequence({ColumnEmpty.class, ColumnLength.class, ColumnPattern.class})
//@GroupSequence({Default.class, ColumnEmpty.class, ColumnLength.class, ColumnMaxLength.class})
//@GroupSequence({ColumnRegexPattern.class, Default.class, ColumnEmpty.class, ColumnLength.class})
//@GroupSequence({ColumnLength.class, ColumnPattern.class})
//@GroupSequence({ColumnPattern.class})
@GroupSequence(value = {Default.class, ColumnEmpty.class, ColumnLength.class, ColumnPattern.class})
public interface ValidationSequence {
}
