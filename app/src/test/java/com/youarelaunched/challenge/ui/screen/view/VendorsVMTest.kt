package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VendorsVMTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var mockVendorsRepository: VendorsRepository
    private lateinit var vendorsVM: VendorsVM
    private val expectedVendor = listOf(
        Vendor(
            id = 1,
            companyName = "companyName",
            coverPhoto = "coverPhoto",
            area = "area",
            favorite = true,
            null,
            null
        )
    )

    @Before
    fun setUp() {
        mockVendorsRepository = mockk()
        vendorsVM = VendorsVM(mockVendorsRepository)
    }

    @Test
    fun `test getVendors - success case`() {
        coEvery { mockVendorsRepository.getVendors("companyName") } returns expectedVendor
        vendorsVM.getVendors("companyName")
        val actual = vendorsVM.uiState.value.vendors
        assertEquals(expectedVendor, actual)
    }

    @Test
    fun `test getVendors - exception case`() {
        coEvery { mockVendorsRepository.getVendors("") } throws Exception("")
        vendorsVM.getVendors("")
        val actual = vendorsVM.uiState.value.vendors
        assertNull(actual)
    }
}