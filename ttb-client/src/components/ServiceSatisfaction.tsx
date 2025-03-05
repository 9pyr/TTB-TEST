import { Button, Form, FormInstance } from "antd"
import { useWatch } from "antd/es/form/Form"
import { range } from "lodash"

interface ServiceSatisfactionProps {
  form: FormInstance
  title?: string
  settings?: Record<string, string>
}

const ServiceSatisfaction = ({
  title,
  settings = {},
  form,
}: ServiceSatisfactionProps) => {
  const { min = "1", max = "5", min_title, max_title, require } = settings

  const isRequired = require === "true"

  const value = useWatch("serviceSatisfaction", form)

  const handleChangeValue = (value: string) => {
    form.setFieldsValue({
      serviceSatisfaction: value,
    })
  }

  const renderRank = () => {
    const start = Number(min)
    const end = Number(max)

    return range(start, end + 1).map((choiceValue, index) => (
      <Button
        key={`rank-button-${choiceValue}:${index}`}
        shape="circle"
        type={value === String(choiceValue) ? "primary" : "dashed"}
        onClick={() => handleChangeValue(String(choiceValue))}
      >
        {choiceValue}
      </Button>
    ))
  }

  return (
    <Form.Item
      name="serviceSatisfaction"
      rules={[{ required: isRequired, message: "โปรดระบุ" }]}
      className="w-full"
    >
      <div className="grid grid-flow-row gap-4">
        <div className="font-bold">{title}</div>
        <div className="flex justify-between px-8">{renderRank()}</div>
        <div className="flex justify-between">
          <span>{min_title}</span>
          <span>{max_title}</span>
        </div>
      </div>
    </Form.Item>
  )
}

export default ServiceSatisfaction
