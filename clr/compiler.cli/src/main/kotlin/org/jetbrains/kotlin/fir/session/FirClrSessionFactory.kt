/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.session

import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.config.languageVersionSettings
import org.jetbrains.kotlin.fir.FirModuleData
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jetbrains.kotlin.fir.java.FirProjectSessionProvider
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker
import org.jetbrains.kotlin.incremental.components.ImportTracker
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.name.Name

object FirClrSessionFactory : FirAbstractSessionFactory<FirClrSessionFactory.LibraryContext, FirClrSessionFactory.SourceContext>() {
    object LibraryContext

    object SourceContext

    fun createLibrarySession(
        mainModuleName: Name,
        sessionProvider: FirProjectSessionProvider,
        moduleDataProvider: ModuleDataProvider,
        extensionRegistrars: List<FirExtensionRegistrar>,
        compilerConfiguration: CompilerConfiguration,
    ): FirSession =
        createLibrarySession(
            mainModuleName,
            LibraryContext,
            sessionProvider,
            moduleDataProvider,
            compilerConfiguration.languageVersionSettings,
            extensionRegistrars,
        ) { session, builtinsModuleData, kotlinScopeProvider, syntheticFunctionInterfaceProvider ->
            listOf()
        }

    fun createModuleBaseSession(
        moduleData: FirModuleData,
        sessionProvider: FirProjectSessionProvider,
        extensionRegistrars: List<FirExtensionRegistrar>,
        lookupTracker: LookupTracker?,
        enumWhenTracker: EnumWhenTracker?,
        importTracker: ImportTracker?,
        compilerConfiguration: CompilerConfiguration,
    ): FirSession =
        createModuleBasedSession(
            moduleData,
            SourceContext,
            sessionProvider,
            extensionRegistrars,
            compilerConfiguration.languageVersionSettings,
            lookupTracker,
            enumWhenTracker,
            importTracker,
            {
            },
        ) { session, kotlinScopeProvider, symbolProvider, generatedSymbolsProvider, dependencies ->
            listOf()
        }

    override fun createKotlinScopeProviderForLibrarySession(): FirKotlinScopeProvider = FirKotlinScopeProvider()

    override fun createKotlinScopeProviderForSourceSession(
        moduleData: FirModuleData,
        languageVersionSettings: LanguageVersionSettings,
    ): FirKotlinScopeProvider = FirKotlinScopeProvider()

    override fun FirSession.registerSourceSessionComponents(c: SourceContext) {
        registerDefaultComponents()
    }

    override fun FirSessionConfigurator.registerPlatformCheckers(c: SourceContext) {
    }

    override fun FirSession.registerLibrarySessionComponents(c: LibraryContext) {
        registerDefaultComponents()
    }
}
