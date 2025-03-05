import { SurveyInput, SurveyModel } from "@/core/apis/types"
import { Step } from "@/core/constants"
import axios from "axios"

const api = axios.create({
  baseURL: "http://localhost:8081/api",
})

export const getSurveyInputByType = async (
  type: Step
): Promise<SurveyInput> => {
  const res = await api.get(`/survey-inputs/${type}`)

  return res.data
}

export const getSurveyById = async (id: string): Promise<SurveyModel> => {
  const res = await api.get(`/survey/${id}`)

  return res.data
}

export const saveSurvey = async (body: SurveyModel) => {
  const res = await api.post("/survey", body)

  return res.data
}
