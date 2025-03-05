"use client"

import Layout from "@/components/Layout"
import SurveyStep from "@/components/SurveyStep"
import { SurveyModel } from "@/core/apis/types"
import { Step } from "@/core/constants"
import useGetSurvey from "@/core/hooks/useGetSurvey"
import useGetSurveyInput from "@/core/hooks/useGetSurveyInput"
import useSaveSurvey from "@/core/hooks/useSaveSurvey"
import removeLocalStorage from "@/utils/removeLocalStorage"
import setLocalStorage from "@/utils/setLocalStorage"
import { Button } from "antd"
import { useForm } from "antd/es/form/Form"
import { redirect } from "next/navigation"
import { useState } from "react"

const SurveyApp = () => {
  const [step, setStep] = useState(Step.Rank)

  const [values, setValues] = useState<Partial<SurveyModel>>({})

  const { data } = useGetSurveyInput(step)

  const { mutateAsync, isPending } = useSaveSurvey()

  const [form] = useForm()

  useGetSurvey(form)

  const handleStep = () => {
    switch (step) {
      case Step.Rank:
        setStep(Step.Radio)
        break

      case Step.Radio:
        setStep(Step.Text)
        break

      default:
        setStep(Step.Rank)
        break
    }
  }

  const handleOnFinish = async (_values: SurveyModel) => {
    const newValues = { ...values, ..._values }
    if (step === Step.Text) {
      await mutateAsync(newValues)

      removeLocalStorage("answers")
      redirect("/survey-complete")
    } else {
      setLocalStorage("answers", newValues)
      setValues(newValues)
      handleStep()
    }
  }

  return (
    <Layout
      onFinish={handleOnFinish}
      form={form}
      footer={
        <Button
          type="primary"
          shape="round"
          className="w-full"
          htmlType="submit"
          loading={isPending}
        >
          ถัดไป
        </Button>
      }
    >
      <SurveyStep
        step={step}
        form={form}
        title={data?.title}
        choices={data?.choices}
        settings={data?.settings}
      />
    </Layout>
  )
}

export default SurveyApp
