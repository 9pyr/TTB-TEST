import { getSurveyById } from "@/core/apis"
import getLocalStorage from "@/utils/getLocalStorage"
import { useQuery } from "@tanstack/react-query"
import { FormInstance } from "antd"
import { useSearchParams } from "next/navigation"

const useGetSurvey = (form: FormInstance) => {
  const searchParams = useSearchParams()

  const seq = searchParams.get("seq")

  const answers = getLocalStorage("answers")

  return useQuery({
    queryKey: ["survey", seq || answers],
    queryFn: async () => {
      let data = answers

      if (seq) {
        data = await getSurveyById(seq)
      }

      console.log("🔥", data)

      form.setFieldsValue(data)

      return data
    },
  })
}

export default useGetSurvey
