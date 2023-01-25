export const getHexColor = (colorName) => {
    // console.log("colorName=", colorName);
    switch (colorName.toLowerCase()) {
      default:
      case 'black': return '#000'
      case 'blue': return '#00f'
      case 'white': return '#fff'
      case 'red': return '#f00'
      case 'yellow': return '#ff0'
      case 'brown': return '741'
      case 'grey':
      case 'gray': return '#aaa'
      case 'green': return '#0f0'
      case 'pink': return '#faf'
      case 'magenta': return '#f0f'
      case 'lightblue':
      case 'light blue':
      case 'cyan': return '#0ff'
    }
  }

export const tryToRequireAsset = (image) => {
  try {
    return require('@/assets/' + image)
  } catch (e) {
    return require('@/assets/images/logo.png')
  }
}