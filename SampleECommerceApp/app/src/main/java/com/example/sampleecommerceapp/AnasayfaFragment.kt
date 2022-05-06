package com.example.sampleecommerceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sampleecommerceapp.databinding.FragmentAnasayfaBinding


class AnasayfaFragment : Fragment() {
    private lateinit var tasarim:FragmentAnasayfaBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentAnasayfaBinding.inflate(inflater, container, false)

        tasarim.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        val productsList = ArrayList<Products>()
        val p1 = Products(
            1,"SAMSUNG GALAXY A33 5G 128 GB AKILLI TELEFON",
            "SM-A336EZKGTUR","samsunga33",6.099)
        val p2 = Products(
            2,"Lenovo Ideapad 5 11.Nesil Core i3 1115G4-4Gb-256Gb",
            "82FE00LBTX","lenovoideapad",5.499)
        val p3 = Products(
            3,"PHILIPS 50PUS7906 50inc 126 CM 4K UHD ANDROID TV",
            "50PUS7906","philipstv",8.999)
        val p4 = Products(
            4,"Ipad Air 64GB WIFI 10.9'' Retina Ekran iPadOS Tablet",
            "MYFQ2TU/A ","ipadair",8.699)
        val p5 = Products(
            5,"Samsung Galaxy A72 128 Gb Akıllı Telefon Siyah",
            "SM-A725FZKGTUR","samsunggalaxya72",6.799)
        val p6 = Products(
            6,"Huawei Matebook D15 10.Nesil Core i3",
            "MBD15I3/8/256","huaweimatebook",7.299)
        val p7 = Products(
            7,"LG 55NANO756 55inc 139 CM NANOCELL 4K UHD",
            "55NANO756PA","lg55nano",11.999)
        val p8 = Products(
            8,"AMD ATHLON 3000G-MSI A320M-A PRO-KIOXIA",
            "VTNMSI 3000G-A320M-A-APU","amdathlon",3.617)
        productsList.add(p1)
        productsList.add(p2)
        productsList.add(p3)
        productsList.add(p4)
        productsList.add(p5)
        productsList.add(p6)
        productsList.add(p7)
        productsList.add(p8)

        val adapter = ProductsAdapter(requireContext(),productsList)
        tasarim.rv.adapter = adapter

        return tasarim.root
    }


}