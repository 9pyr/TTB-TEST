import isSSR from "@/utils/isSSR"

export default function setLocalStorage(
  key: string,
  value: Record<string, unknown>
) {
  if (isSSR()) {
    window.localStorage.setItem(key, JSON.stringify(value))
  }
}
