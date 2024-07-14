package ru.practicum.android.diploma.details.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentJobDetailsBinding
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.details.presentation.state.VacancyDetailsState
import ru.practicum.android.diploma.details.presentation.viewmodel.VacancyDetailsViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyDetailsViewModel by viewModel<VacancyDetailsViewModel> {
        parametersOf(requireArguments().getString(VACANCY_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            initializeJobDetailsFragment(it)
        }
        viewModel.isFavourite.observe(viewLifecycleOwner) {
            if (it) {
                binding.jobHeartIcon.setImageResource(R.drawable.icon_heart_active)
            } else {
                binding.jobHeartIcon.setImageResource(R.drawable.icon_heart)
            }
        }

        binding.jobShareIcon.setOnClickListener {
            viewModel.shareVacancy()
        }

        binding.jobHeartIcon.setOnClickListener {
            viewModel.onFavoriteClicked()
        }

        binding.jobContactsEmail.setOnClickListener {
            viewModel.sendEmail(binding.jobContactsEmail.text.toString())
        }

        binding.jobContactsPhone.setOnClickListener {
            viewModel.dialNumber(binding.jobContactsPhone.text.toString())
        }

        binding.jobArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initializeJobDetailsFragment(vacancyDetailsState: VacancyDetailsState) {
        setVisibilityOfMainElements(vacancyDetailsState)
        if (vacancyDetailsState is VacancyDetailsState.Content) {
            val vacancyDetails: VacancyDetails = vacancyDetailsState.data
            binding.jobTitle.text = vacancyDetails.name
            binding.jobSalary.text = if (vacancyDetails.salary == null) {
                binding.root.resources.getString(R.string.no_salary_msg)
            } else if (vacancyDetails.salary.to == null) {
                binding.root.resources.getString(
                    R.string.salary_from,
                    formatSalaryAmount(vacancyDetails.salary.from),
                    convertCurrencyToSymbol(vacancyDetails)
                )
            } else {
                binding.root.resources.getString(
                    R.string.salary_range,
                    formatSalaryAmount(vacancyDetails.salary.from),
                    formatSalaryAmount(vacancyDetails.salary.to),
                    convertCurrencyToSymbol(vacancyDetails)
                )
            }

            binding.jobCompany.text = vacancyDetails.employer?.name
            binding.jobCity.text = vacancyDetails.area.name

            binding.jobExperienceText1.text = vacancyDetails.experience?.name
            binding.jobExperienceText2.text = vacancyDetails.employment?.name

            if (vacancyDetails.description.isEmpty()) {
                binding.jobDescriptionText.isVisible = false
                binding.jobDescriptionTitle.isVisible = false
            } else {
                val htmlTextOriginal = vacancyDetails.description
                val htmlTextModified = htmlTextOriginal.replace("<li>", "<li>&nbsp;") //убрать?
                binding.jobDescriptionText.setText(
                    Html.fromHtml(
                        htmlTextModified,
                        Html.FROM_HTML_MODE_COMPACT
                    )
                )
            }

            if (vacancyDetails.keySkills.isEmpty()) {
                binding.jobKeySkillsTitle.isVisible = false
            } else {
                binding.jobKeySkillsText.text =
                    getString(R.string.bullet_point) + vacancyDetails.keySkills.joinToString(
                        separator = "\n${getString(R.string.bullet_point)}"
                    )
            }

            binding.jobContactsEmail.isVisible = vacancyDetails.contacts?.email != null
            binding.jobContactsPhone.isVisible = !vacancyDetails.contacts?.phones.isNullOrEmpty()
            if (!binding.jobContactsEmail.isVisible && !binding.jobContactsPhone.isVisible) {
                binding.jobContactsTitle.isVisible = false
            }

            if (vacancyDetails.contacts != null && binding.jobContactsTitle.isVisible) {
                binding.jobContactsEmail.text = "${binding.jobContactsEmail.text} ${vacancyDetails.contacts.email}"
                binding.jobContactsPhone.text =
                    "${binding.jobContactsPhone.text} ${vacancyDetails.contacts.phones?.joinToString(separator = "\n")}"
            }

            Glide.with(binding.jobImage)
                .load(vacancyDetails.employer?.logoUrls?.size240)
                .transform(RoundedCorners(ROUNDED_CORNERS_SIZE))
                .placeholder(R.drawable.placeholder_logo)
                .into(binding.jobImage)
        }
    }

    private fun formatSalaryAmount(salaryAmount: Int?): String {
        val delimiterSymbol = DecimalFormatSymbols().apply { groupingSeparator = ' ' }
        val numberFormat = DecimalFormat("###,###,###,###,###", delimiterSymbol)
        return numberFormat.format(salaryAmount).toString()
    }

    private fun convertCurrencyToSymbol(vacancy: VacancyDetails): String { // в Utils?
        var currencyCode = vacancy.salary?.currency
        if (currencyCode == "RUR") currencyCode = "RUB"
        val currency = java.util.Currency.getInstance(currencyCode)
        return currency.symbol
    }

    private fun setVisibilityOfMainElements(vacancyDetailsState: VacancyDetailsState) {
        binding.jobInfo.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobTitle.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobSalary.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobCard.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobImage.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobCompany.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobCity.isVisible = vacancyDetailsState is VacancyDetailsState.Content
        binding.jobPlaceholderImage.isVisible = vacancyDetailsState is VacancyDetailsState.Error
        binding.jobPlaceholderText.isVisible = vacancyDetailsState is VacancyDetailsState.Error
        binding.loadingProgressBar.isVisible = vacancyDetailsState is VacancyDetailsState.Loading
    }

    companion object {

        private const val VACANCY_ID = "vacancy_id"
        private const val ROUNDED_CORNERS_SIZE = 12
        fun createArgs(vacancyID: String): Bundle = bundleOf(VACANCY_ID to vacancyID)
    }
}
