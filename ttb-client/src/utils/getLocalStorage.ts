import isSSR from "@/utils/isSSR"

export default function getLocalStorage(key: string) {
  if (isSSR()) {
    const values = window.localStorage.getItem(key)
    return values && JSON.parse(values)
  }
  return {}
}
