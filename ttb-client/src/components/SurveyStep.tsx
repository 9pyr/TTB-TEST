import FeedbackComments from "@/components/FeedbackComments"
import ImprovementSuggestion from "@/components/ImprovementSuggestion"
import ServiceSatisfaction from "@/components/ServiceSatisfaction"
import { Choice, Setting } from "@/core/apis/types"
import { Step } from "@/core/constants"
import { FormInstance } from "antd"
import { reduce } from "lodash"
import { useMemo } from "react"

interface SurveyStepProps {
  step: Step
  form: FormInstance
  title?: string
  choices?: Choice[]
  settings?: Setting[]
}

const SurveyStep = ({
  step,
  form,
  title,
  choices,
  settings: initialSettings,
}: SurveyStepProps) => {
  const settings = useMemo(
    () =>
      reduce(
        initialSettings,
        (prev, curr) => ({
          ...prev,
          [curr.key]: curr.value,
        }),
        {}
      ),
    [initialSettings]
  )

  const renderStep = () => {
    switch (step) {
      case Step.Rank:
        return (
          <ServiceSatisfaction title={title} settings={settings} form={form} />
        )

      case Step.Radio:
        return (
          <ImprovementSuggestion
            title={title}
            choices={choices}
            settings={settings}
            form={form}
          />
        )

      case Step.Text:
        return (
          <FeedbackComments title={title} settings={settings} form={form} />
        )

      default:
        return null
    }
  }

  return renderStep()
}

export default SurveyStep
