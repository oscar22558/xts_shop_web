package com.xtsshop.app.assembler;

import com.xtsshop.app.viewmodel.AbstractViewModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public abstract class AbstractModelAssembler<VM extends AbstractViewModel, E>
        implements RepresentationModelAssembler<E, EntityModel<VM>> { }
