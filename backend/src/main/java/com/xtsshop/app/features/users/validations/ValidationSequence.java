package com.xtsshop.app.features.users.validations;


import com.xtsshop.app.features.users.validations.groups.ColumnEmpty;
import com.xtsshop.app.features.users.validations.groups.ColumnLength;
import com.xtsshop.app.features.users.validations.groups.ColumnPattern;
import org.apache.naming.java.javaURLContextFactory;

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
