package ru.practicum.android.diploma.details.presentation

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
import ru.practicum.android.diploma.utils.CurrencySymbol
import ru.practicum.android.diploma.utils.formatSalaryAmount

class JobDetailsFragment : Fragment() {

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyDetailsViewModel by viewModel<VacancyDetailsViewModel> {
        parametersOf(requireArguments().getString(VACANCY_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            initializeJobDetailsFragment(it)
        }
        viewModel.isFavorite.observe(viewLifecycleOwner) {
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

            renderJobSalary(vacancyDetails)

            binding.jobCompany.text = vacancyDetails.employerInfo.employerName
            binding.jobCity.text = vacancyDetails.employerInfo.area.name

            renderJobExperience(vacancyDetails)

            renderJobDetails(vacancyDetails)

            renderKeySkills(vacancyDetails)

            renderContacts(vacancyDetails)

            Glide.with(binding.jobImage)
                .load(vacancyDetails.employerInfo.logo?.size240)
                .transform(RoundedCorners(ROUNDED_CORNERS_SIZE))
                .placeholder(R.drawable.placeholder_logo)
                .into(binding.jobImage)
        }
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
        if (vacancyDetailsState is VacancyDetailsState.Error) {
            renderErrorPlaceholder(vacancyDetailsState.message)
        }

    }

    private fun renderJobSalary(vacancyDetails: VacancyDetails) {
        binding.jobSalary.text = when {
            vacancyDetails.jobInfo.salary == null -> binding.root.resources.getString(R.string.no_salary_msg)

            vacancyDetails.jobInfo.salary.from == null && vacancyDetails.jobInfo.salary.to != null -> {
                binding.root.resources.getString(
                    R.string.salary_to,
                    formatSalaryAmount(vacancyDetails.jobInfo.salary.to),
                    CurrencySymbol.getCurrencySymbol(vacancyDetails.jobInfo.salary.currency)
                )
            }

            vacancyDetails.jobInfo.salary.to == null -> {
                binding.root.resources.getString(
                    R.string.salary_from,
                    formatSalaryAmount(vacancyDetails.jobInfo.salary.from),
                    CurrencySymbol.getCurrencySymbol(vacancyDetails.jobInfo.salary.currency)
                )
            }

            else -> {
                binding.root.resources.getString(
                    R.string.salary_range,
                    formatSalaryAmount(vacancyDetails.jobInfo.salary.from),
                    formatSalaryAmount(vacancyDetails.jobInfo.salary.to),
                    CurrencySymbol.getCurrencySymbol(vacancyDetails.jobInfo.salary.currency)
                )
            }
        }
    }

    private fun renderJobExperience(vacancyDetails: VacancyDetails) {
        if (vacancyDetails.jobInfo.experience != null) {
            binding.jobExperienceText1.text = vacancyDetails.jobInfo.experience
        } else {
            binding.jobExperienceText1.isVisible = false
        }
        if (vacancyDetails.jobInfo.employment != null) {
            binding.jobExperienceText2.text = vacancyDetails.jobInfo.employment
        } else {
            binding.jobExperienceText2.isVisible = false
        }
        binding.jobExperienceTitle.isVisible =
            binding.jobExperienceText1.isVisible || binding.jobExperienceText2.isVisible
    }

    private fun renderJobDetails(vacancyDetails: VacancyDetails) {
        if (vacancyDetails.description.isEmpty()) {
            binding.jobDescriptionText.isVisible = false
            binding.jobDescriptionTitle.isVisible = false
        } else {
            val htmlTextOriginal = vacancyDetails.description
            val htmlTextModified = htmlTextOriginal.replace("<li>", "<li>&nbsp;")
            binding.jobDescriptionText.setText(
                Html.fromHtml(
                    htmlTextModified,
                    Html.FROM_HTML_MODE_COMPACT
                )
            )
        }
    }

    private fun renderKeySkills(vacancyDetails: VacancyDetails) {
        if (vacancyDetails.jobInfo.keySkills.isEmpty()) {
            binding.jobKeySkillsTitle.isVisible = false
        } else {
            binding.jobKeySkillsText.text =
                getString(R.string.bullet_point) + vacancyDetails.jobInfo.keySkills.joinToString(
                    separator = "\n${getString(R.string.bullet_point)}"
                )
        }
    }

    private fun renderContacts(vacancyDetails: VacancyDetails) {
        binding.jobContactsEmail.isVisible = vacancyDetails.employerInfo.contacts?.email != null
        binding.jobContactsPhone.isVisible = !vacancyDetails.employerInfo.contacts?.phones.isNullOrEmpty()
        if (!binding.jobContactsEmail.isVisible && !binding.jobContactsPhone.isVisible) {
            binding.jobContactsTitle.isVisible = false
        }

        if (vacancyDetails.employerInfo.contacts != null && binding.jobContactsTitle.isVisible) {
            binding.jobContactsEmail.text =
                "${binding.jobContactsEmail.text} ${vacancyDetails.employerInfo.contacts.email}"
            binding.jobContactsPhone.text =
                "${binding.jobContactsPhone.text} " +
                    "${vacancyDetails.employerInfo.contacts.phones?.joinToString(separator = "\n")}"
        }
    }

    private fun renderErrorPlaceholder(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            binding.jobPlaceholderText.text = requireActivity().getString(R.string.no_internet)
            binding.jobPlaceholderImage.background = requireActivity().getDrawable(R.drawable.picture_funny_head)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val VACANCY_ID = "vacancy_id"
        private const val ROUNDED_CORNERS_SIZE = 12
        fun createArgs(vacancyId: String): Bundle = bundleOf(VACANCY_ID to vacancyId)
    }
}
