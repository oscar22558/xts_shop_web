package com.xtsshop.app;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public abstract class AbstractModelAssembler<VM extends AbstractRepresentationModel, E>
        implements RepresentationModelAssembler<E, EntityModel<VM>> { }
