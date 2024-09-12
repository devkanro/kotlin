/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.il.generator

import org.jetbrains.kotlin.generators.tree.ImplementationKind
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
        parent(classMember)
        parent(eventMember)
        parent(methodBodyItem)
        parent(propertyMember)

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
        parent(classMember)
        parent(eventMember)
        parent(propertyMember)
        parent(methodBodyItem)
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
        parent(classMember)

        +listField("attribs", classAttr)
        +field("name", id)
        +listField("genPars", genPar)
        +field("extends", typeSpec)
        +listField("implements", typeSpec)
        +listField("members", classMember)
    }

    val classAttr: Element by element(Element.Category.Primitive) {
        kind = ImplementationKind.Interface
    }

    val classVisibilityAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)

        +field("visibility", classVisibilityType)
    }

    val classLayoutAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classSemanticsAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classInheritanceAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classInteroperationAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classBeforeFieldInitAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classRuntimeSpecialNameAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classSpecialNameAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classSerializableAttr: Element by element(Element.Category.Primitive) {
        parent(classAttr)
    }

    val classMember: Element by element(Element.Category.Primitive) {
        kind = ImplementationKind.Interface
    }

    val dataDecl: Element by element(Element.Category.Declaration) {
        parent(methodBodyItem)

        +field("label", id)
        +listField("items", dataItem)
    }

    val dataItem: Element by element(Element.Category.Declaration) {
        kind = ImplementationKind.Interface
    }

    val labelAddressDataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field("label", id)
    }

    val byteArrayDataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<ByteArray>("value")
    }

    val stringDataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<String>("value")
    }

    val float32DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Float>("value")
        +field<Int>("replications")
    }

    val float64DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Double>("value")
        +field<Int>("replications")
    }

    val int8DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Byte>("value")
        +field<Int>("replications")
    }

    val int16DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Short>("value")
        +field<Int>("replications")
    }

    val int32DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Int>("value")
        +field<Int>("replications")
    }

    val int64DataItem: Element by element(Element.Category.Primitive) {
        parent(dataItem)

        +field<Long>("value")
        +field<Int>("replications")
    }

    val genPar: Element by element(Element.Category.Primitive) {
        +listField("attribs", genParAttr)
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

    val typeSpec: Element by element(Element.Category.Types) {
        kind = ImplementationKind.Interface
    }

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
        +listField("parameters", param)
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

    val eventDecl: Element by element(Element.Category.Declaration) {
        parent(classMember)

        +field<Boolean>("hasSpecialName")
        +field<Boolean>("hasRuntimeSpecialName")
        +field("delegateType", typeSpec)
        +field("id", id)
        +listField("members", eventMember)
    }

    val eventMember: Element by element(Element.Category.Primitive) {
        kind = ImplementationKind.Interface
    }

    val eventMethodDecl: Element by element(Element.Category.Declaration) {
        parent(eventMember)

        +field("kind", eventKindType)
        +field("callConv", callConv)
        +field("returnType", type)
        +field("declType", typeSpec, nullable = true)
        +field("methodName", dottedName)
        +listField("parameters", param)
    }

    val fieldDecl: Element by element(Element.Category.Declaration) {
        parent(decl)
        parent(classMember)

        +field<Int>("offset", nullable = true)
        +listField("attribs", fieldAttr)
        +field("type", type)
        +field("id", id)
        +field("init", fieldInitDecl, nullable = true)
        +field("data", id, nullable = true)
    }

    val fieldAttr: Element by element(Element.Category.Primitive) {
        kind = ImplementationKind.Interface
    }

    val fieldVisibilityAttr: Element by element((Element.Category.Primitive)) {
        parent(fieldAttr)

        +field("visibility", memberVisibilityType)
    }

    val fieldContractAttr: Element by element((Element.Category.Primitive)) {
        parent(fieldAttr)

        +field("contract", fieldContractKindType)
    }

    val fieldInteropAttr: Element by element((Element.Category.Primitive)) {
        parent(fieldAttr)

        +field("marshalAs", nativeType)
    }

    val fieldInitDecl: Element by element((Element.Category.Primitive)) {
        kind = ImplementationKind.Interface
    }

    val fieldBoolInitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Boolean>("value")
    }

    val fieldByteArrayInitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<ByteArray>("value")
    }

    val fieldCharInitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Int>("value")
    }

    val fieldFloat32InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Float>("value")
    }

    val fieldFloat64InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Double>("value")
    }

    val fieldInt8InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Int>("value")
        +field<Boolean>("unsigned")
    }

    val fieldInt16InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Int>("value")
        +field<Boolean>("unsigned")
    }

    val fieldInt32InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Int>("value")
        +field<Boolean>("unsigned")
    }

    val fieldInt64InitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<Long>("value")
        +field<Boolean>("unsigned")
    }

    val fieldStringInitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)

        +field<String>("value")
    }

    val fieldNullInitDecl: Element by element((Element.Category.Primitive)) {
        parent(fieldInitDecl)
    }

    val overrideDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)

        +field("type", typeSpec)
        +field("method", id)
        +field("implType", typeSpec)
        +field("implMethod", id)
        +field("callConv", callConv)
        +field("returnType", type)
        +listField("parameters", param)
    }

    val overrideExDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)

        +field("callConv", callConv)
        +field("returnType", type)
        +field("type", typeSpec)
        +field("method", id)
        +field<Int>("genArity")
        +field("implCallConv", callConv)
        +field("implReturnType", type)
        +field("implType", typeSpec)
        +field("implMethod", id)
        +field<Int>("implGenArity")
        +listField("parameters", param)
    }

    val packDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)

        +field<Int>("value")
    }

    val sizeDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)

        +field<Int>("value")
    }

    val propertyDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)

        +field<Boolean>("hasSpecialName")
        +field<Boolean>("hasRuntimeSpecialName")
        +field("callConv", callConv)
        +field("type", type)
        +field("id", id)
        +listField("parameters", param)
    }

    val propertyMember: Element by element((Element.Category.Declaration)) {
        kind = ImplementationKind.Interface
    }

    val propertyMethodDecl: Element by element(Element.Category.Declaration) {
        parent(propertyMember)

        +field("kind", propertyKindType)
        +field("callConv", callConv)
        +field("returnType", type)
        +field("declType", typeSpec, nullable = true)
        +field("methodName", dottedName)
        +listField("parameters", param)
    }

    val typeParamDecl: Element by element((Element.Category.Declaration)) {
        parent(classMember)
        parent(methodBodyItem)

        +field<Int>("index")
    }

    val methodDecl: Element by element((Element.Category.Declaration)) {
        parent(decl)
        parent(classMember)

        +listField("attribs", methodAttr)
        +field("callConv", callConv)
        +field("returnType", type)
        +field("marshalAs", nativeType, nullable = true)
        +field("id", id)
        +listField("genPars", genPar)
        +listField("parameters", param)
        +listField("implAttribs", methodImplAttrKind)
        +listField("items", methodBodyItem)
    }

    val methodAttr: Element by element((Element.Category.Primitive)) {
        kind = ImplementationKind.Interface
    }

    val methodVisibilityAttr: Element by element((Element.Category.Primitive)) {
        parent(methodAttr)

        +field("visibility", memberVisibilityType)
    }

    val methodContractAttr: Element by element((Element.Category.Primitive)) {
        parent(methodAttr)

        +field("contract", fieldContractKindType)
    }

    val methodInteropAttr: Element by element((Element.Category.Primitive)) {
        parent(methodAttr)

        +field<String>("moduleName")
        +field<String>("methodName", nullable = true)
        +listField("attribs", platformInvokeKind)
    }

    val methodBodyItem: Element by element((Element.Category.Primitive)) {
        kind = ImplementationKind.Interface
    }

    val emitByteDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field<Int>("value")
    }

    val entrypointDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)
    }

    val localsDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field<Boolean>("shouldInit")
        +listField("locals", localDecl)
    }

    val localDecl: Element by element((Element.Category.Declaration)) {
        +field("type", type)
        +field("id", id)
    }

    val maxStackDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field<Int>("value")
    }

    val methodOverrideDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field("type", typeSpec)
        +field("method", id)
    }

    val methodOverrideExDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field("callConv", callConv)
        +field("returnType", type)
        +field("type", typeSpec)
        +field("method", id)
        +field<Int>("genArity")
        +listField("parameters", param)
    }

    val constantParamDecl: Element by element((Element.Category.Declaration)) {
        parent(methodBodyItem)

        +field<Int>("parameter")
        +field("init", fieldInitDecl, nullable = true)
    }

    val methodScope: Element by element((Element.Category.Primitive)) {
        parent(methodBodyItem)

        +listField("items", methodBodyItem)
    }

    val instr: Element by element(Element.Category.Instruction) {
        kind = ImplementationKind.Interface

        +field("op", opCodeType)
    }

    val instrBrTarget: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrField: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrI: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrI8: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrMethod: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrNone: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrPhi: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrR: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrRva: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrSig: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrString: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrSwitch: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrTok: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrType: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }

    val instrVar: Element by element(Element.Category.Instruction) {
        parent(instr)

        kind = ImplementationKind.Interface
    }
}
