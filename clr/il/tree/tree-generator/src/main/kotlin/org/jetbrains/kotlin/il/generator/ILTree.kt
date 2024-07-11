/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.generators.tree.type
import org.jetbrains.kotlin.il.generator.config.AbstractILTreeBuilder
import org.jetbrains.kotlin.il.generator.model.Element
import org.jetbrains.kotlin.il.generator.model.SimpleField

object ILTree : AbstractILTreeBuilder() {
    private fun descriptor(
        typeName: String,
        nullable: Boolean = false,
    ): SimpleField =
        field(
            name = "descriptor",
            type = type(Packages.descriptors, typeName),
            mutable = false,
            nullable = nullable,
        ) {}

    override val rootElement: Element by element(Element.Category.Other, name = "Element") {
        kDoc = "The root interface of the IL tree. Each IL node implements this interface."
    }

    val id: Element by element(Element.Category.Primitive) {
        +field<String>("value")
    }

    val dottedName: Element by element(Element.Category.Primitive) {
        +listField("names", id)
    }

    val fileName: Element by element(Element.Category.Primitive) {
        parent(dottedName)
    }

    val externSourceDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field<String>("file")
        +field<Int>("line")
        +field<Int>("column", nullable = true)
    }

    val decl: Element by element(Element.Category.Primitive) {}

    val file: Element by element(Element.Category.Other) {
        +listField("decls", decl)
    }

    val assembly: Element by element(Element.Category.Other) {
        +listField("decls", decl)
    }

    val assemblyDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field("name", dottedName)
        +listField("decls", asmDecl)
    }

    val asmDecl: Element by element(Element.Category.Primitive) {}

    val asmHashDecl: Element by element(Element.Category.Declaration) {
        parent(asmDecl)

        +field<Int>("algorithm")
    }

    val asmCultureDecl: Element by element(Element.Category.Declaration) {
        parent(asmDecl)

        +field<String>("value")
    }

    val asmPublicKeyDecl: Element by element(Element.Category.Declaration) {
        parent(asmDecl)

        +field<ByteArray>("value")
    }

    val asmVerDecl: Element by element(Element.Category.Declaration) {
        parent(asmDecl)

        +field<Int>("major")
        +field<Int>("minor")
        +field<Int>("build")
        +field<Int>("revision")
    }

    val securityDecl: Element by element(Element.Category.Primitive) {
        parent(asmDecl)
    }

    val assemblyRefDecl: Element by element(Element.Category.Declaration) {
        parent(decl)
        parent(manResDecl)

        +field("name", dottedName)
        +listField("decls", asmRefDecl)
    }

    val asmRefDecl: Element by element(Element.Category.Primitive) {}

    val asmRefHashDecl: Element by element(Element.Category.Declaration) {
        parent(asmRefDecl)

        +field<ByteArray>("hash")
    }

    val asmRefCultureDecl: Element by element(Element.Category.Declaration) {
        parent(asmRefDecl)

        +field<String>("value")
    }

    val asmRefPublicKeyTokenDecl: Element by element(Element.Category.Declaration) {
        parent(asmRefDecl)

        +field<ByteArray>("value")
    }

    val asmRefPublicKeyDecl: Element by element(Element.Category.Declaration) {
        parent(asmRefDecl)

        +field<ByteArray>("value")
    }

    val asmRefVersionDecl: Element by element(Element.Category.Declaration) {
        parent(asmRefDecl)

        +field<Int>("major")
        +field<Int>("minor")
        +field<Int>("build")
        +field<Int>("revision")
    }

    val corflagsDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field<Int>("value")
    }

    val fileDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field<Boolean>("hasMetadata")
        +field<Boolean>("hasEntrypoint")
        +field<ByteArray>("hash")
    }

    val modeRefDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field("file", fileName)
    }

    val mresourceDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field<Boolean>("isPrivate")
        +field("name", dottedName)
        +listField("decls", manResDecl)
    }

    val manResDecl: Element by element(Element.Category.Primitive) {}

    val manResFileDecl: Element by element(Element.Category.Declaration) {
        parent(manResDecl)

        +field("name", dottedName)
        +field<Int>("offset")
    }

    val subsystemDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field<Int>("value")
    }

    val customDecl: Element by element(Element.Category.Declaration) {
        parent(decl)
        parent(asmDecl)
        parent(manResDecl)
        parent(asmRefDecl)
    }

    val moduleDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field("file", fileName)
    }

    val moduleRefDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +field("file", fileName)
    }

    val classDecl: Element by element(Element.Category.Declaration) {
        parent(decl)

        +listField("attributes", classAttr)
        +field("name", id)
    }

    val classAttr: Element by element(Element.Category.Primitive) {}

    val genPar: Element by element(Element.Category.Primitive) {
        +listField("genParAttribs", genParAttr)
        +listField("genConstraints", type)
        +field("id", id)
    }

    val genParAttr: Element by element(Element.Category.Primitive) {}

    val covariantGenParAttr: Element by element(Element.Category.Primitive) {
        parent(genParAttr)
    }

    val contravariantGenParAttr: Element by element(Element.Category.Primitive) {
        parent(genParAttr)
    }

    val classGenParAttr: Element by element(Element.Category.Primitive) {
        parent(genParAttr)
    }

    val valuetypeGenParAttr: Element by element(Element.Category.Primitive) {
        parent(genParAttr)
    }

    val defaultConstructorGenParAttr: Element by element(Element.Category.Primitive) {
        parent(genParAttr)
    }

    val typeSpec: Element by element(Element.Category.Types) {}

    val typeReference: Element by element(Element.Category.Types) {
        parent(typeSpec)

        +field("resolutionScope", resolutionScope, nullable = true)
        +field("name", dottedName)
        +field("nested", dottedName, nullable = true)
    }

    val resolutionScope: Element by element(Element.Category.Primitive) {}

    val moduleResolutionScope: Element by element(Element.Category.Primitive) {
        parent(resolutionScope)

        +field("module", fileName)
    }

    val assemblyResolutionScope: Element by element(Element.Category.Primitive) {
        parent(resolutionScope)

        +field("assembly", dottedName)
    }

    val methodPointType: Element by element(Element.Category.Types) {
        parent(type)

        +field("callConv", callConv)
        +field("returnType", type)
        +field("parameters", param)
    }

    val customModifierType by element(Element.Category.Types) {
        parent(type)

        +field<Boolean>("isRequired")
        +field("type", type)
        +field("modifier", typeReference)
    }

    val classType by element(Element.Category.Types) {
        parent(type)

        +field("type", typeReference)
    }

    val valueType by element(Element.Category.Types) {
        parent(type)

        +field("type", typeReference)
    }

    val callConv: Element by element(Element.Category.Primitive) {
        +field<Boolean>("isInstance")
        +field<Boolean>("isExplicit")
        +field("kind", callKindType)
    }

    val param: Element by element(Element.Category.Primitive) {
        +field<Boolean>("hasInAtt")
        +field<Boolean>("hasOutAtt")
        +field<Boolean>("hasOptAtt")
        +field("type", type)
        +field("marshalAs", nativeType)
        +field("name", id, nullable = true)
    }
}
